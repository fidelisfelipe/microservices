package com.example.microservices.workflow.controller;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
import com.example.microservices.workflow.bean.History;
import com.example.microservices.workflow.config.StateMachineConfiguration;
import com.example.microservices.workflow.event.StateMachineErrorEvent;
import com.example.microservices.workflow.response.FlowResponse;
import com.example.microservices.workflow.response.HistoryResponse;
import com.example.microservices.workflow.response.MessageResponse;
import com.example.microservices.workflow.service.FlowService;
import com.example.microservices.workflow.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/flow")
public class StateMachineController {

    private final FlowService service;
    private final HistoryService historyService;

    @EventListener
    public void handleStateMachineError(StateMachineErrorEvent event) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        attributes.setAttribute("stateMachineError", event.message(), RequestAttributes.SCOPE_REQUEST);
    }

    @GetMapping("create")
    public ResponseEntity<FlowResponse> createFlow(){
        log.info("create");
        var fluxo = Flow.builder()
                .state(FlowStates.CRIADO.name())
                .event(FlowEvents.CRIAR.name())
                .dateTime(LocalDateTime.now()).build();

        fluxo.setDataType(StateMachineConfiguration.ACCEPT);//token validate guard condition
        service.save(fluxo);
        return this.changeState(fluxo.getId(), fluxo.getEvent());
    }

    @GetMapping("{id}")
    public ResponseEntity state(@PathVariable String id){
        log.info("get state");
        Optional<Flow> fluxo = service.findById(id);

        if(fluxo.isPresent())
            return ResponseEntity.status(OK).body(FlowResponse.builder()
                    .id(fluxo.get().getId())
                    .state(fluxo.get().getState())
                    .dataTime(fluxo.get().getDateTime())
                    .build());

        return ResponseEntity.status(NOT_FOUND).body(
                MessageResponse.builder()
                        .status(NOT_FOUND.value())
                        .message(String.format(String.format("flow not found id:%s", id))).build());
    }

    @GetMapping("history/{id}")
    public ResponseEntity historyByFlow(@PathVariable String id){
        log.info("get state");
        Optional<Flow> flow = service.findById(id);

        if(flow.isPresent()) {
            Optional<History> history = historyService.findFirstByFluxoOrderByCreationDateTimeDesc(flow.get());

            if(history.isPresent()) {
                var responseHistory = HistoryResponse.builder()
                        .id(history.get().getId())
                        .state(history.get().getState())
                        .creationDateTime(history.get().getDateTime())
                        .build();
                return ResponseEntity.status(OK).body(responseHistory);
            }
            return ResponseEntity.status(NOT_FOUND).body(
                    MessageResponse.builder()
                        .status(NOT_FOUND.value())
                        .message(String.format(String.format("history not found id:%s", id))).build());
        }

        return ResponseEntity.status(NOT_FOUND).body(MessageResponse.builder()
                .status(NOT_FOUND.value())
                .message(String.format(String.format("flow not found id:%s", id))).build());
    }

    @GetMapping("history/{id}/list")
    public ResponseEntity historyListByFlow(@PathVariable String id){
        log.info("get state");
        Optional<Flow> flow = service.findById(id);

        if(flow.isPresent()) {
            Optional<List<History>> historyList = historyService.findByFluxoOrderByDateTimeDesc(flow.get());

            if(historyList.isPresent()) {

                var responseHistoryList = historyList.get().stream().map(history -> HistoryResponse.builder()
                        .id(history.getId())
                        .state(history.getState())
                        .creationDateTime(history.getDateTime())
                        .build()).toList();

                return ResponseEntity.status(OK).body(responseHistoryList);
            }
            return ResponseEntity.status(NOT_FOUND).body(
                    MessageResponse.builder()
                            .status(NOT_FOUND.value())
                            .message(String.format(String.format("history not found id:%s", id))).build());
        }

        return ResponseEntity.status(NOT_FOUND).body(MessageResponse.builder()
                .status(NOT_FOUND.value())
                .message(String.format(String.format("flow not found id:%s", id))).build());
    }

    @PutMapping("{event}/{id}")
    public ResponseEntity changeState(@PathVariable String id, @PathVariable String event){
        log.info("change state");

        var flow = Flow.builder()
                .id(id)
                .event(event)
                .dataType(StateMachineConfiguration.ACCEPT)//token validate guard condition
                .build();

        var sended = service.sendEvent(flow);

        log.info("sended event {}", sended);

        String stateMachineError = (String) RequestContextHolder
                                    .getRequestAttributes()
                                    .getAttribute("stateMachineError", RequestAttributes.SCOPE_REQUEST);

        if (stateMachineError != null)
            return ResponseEntity.badRequest().body(
                    MessageResponse.builder()
                            .status(BAD_REQUEST.value())
                            .message(stateMachineError).build());


        var flowOptional = service.findById(flow.getId());

        if(flowOptional.isPresent()) {
            var response = FlowResponse.builder()
                    .id(flowOptional.get().getId())
                    .state(flowOptional.get().getState())
                    .dataTime(flowOptional.get().getDateTime())
                    .build();
            return ResponseEntity.status(CREATED).body(response);
        }

        return ResponseEntity.status(NOT_FOUND).body(
                MessageResponse.builder()
                        .status(NOT_FOUND.value())
                        .message(String.format("flow not found id:%s", flow.getId())).build());
    }

}