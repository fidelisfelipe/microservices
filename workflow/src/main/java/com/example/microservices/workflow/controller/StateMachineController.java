package com.example.microservices.workflow.controller;

import com.example.microservices.workflow.annotations.StandardFlowResponses;
import com.example.microservices.workflow.annotations.StandardHistoryResponses;
import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
import com.example.microservices.workflow.bean.History;
import com.example.microservices.workflow.config.StateMachineConfiguration;
import com.example.microservices.workflow.response.FlowResponse;
import com.example.microservices.workflow.response.HistoryResponse;
import com.example.microservices.workflow.response.MessageResponse;
import com.example.microservices.workflow.service.FlowService;
import com.example.microservices.workflow.service.HistoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@OpenAPIDefinition(info = @Info(title = "Workflow API", version = "1.0", description = "Workflow service"))
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/flow")
public class StateMachineController {

    private final FlowService service;
    private final HistoryService historyService;

    @Operation(summary = "Create a new flow", description = "Create a new flow", tags = { "flow" })
    @StandardFlowResponses
    @PostMapping("create")
    public ResponseEntity<FlowResponse> createFlow(){
        log.info("create flow");
        var flow = Flow.builder()
                .state(FlowStates.CRIADO.name())
                .event(FlowEvents.CRIAR.name())
                .dateTime(LocalDateTime.now()).build();

        flow.setDataType(StateMachineConfiguration.ACCEPT);//token validate guard condition
        service.save(flow);
        return this.changeState(flow.getId(), flow.getEvent());
    }

    @Operation(summary = "Get flow", description = "Get flow", tags = { "flow" })
    @StandardFlowResponses
    @GetMapping("{id}")
    public ResponseEntity getFlow(@PathVariable String id){
        log.info("get flow");
        Optional<Flow> flow = service.findById(id);

        return flow.map(f -> ResponseEntity.ok(toFlowResponse(f)))
                .orElseGet(() -> errorResponse(NOT_FOUND, "flow not found id: " + id));
    }

    private ResponseEntity errorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(
                MessageResponse.builder().status(status.value()).message(message).build()
        );
    }

    private FlowResponse toFlowResponse(Flow flow) {
        return FlowResponse.builder()
                .id(flow.getId())
                .state(flow.getState())
                .dataTime(flow.getDateTime())
                .build();
    }

    @Operation(summary = "Get last history", description = "Get last history", tags = { "flow" })
    @StandardHistoryResponses
    @GetMapping("history/{id}")
    public ResponseEntity lastHistoryByFlow(@PathVariable String id){
        log.info("get history");
        return service.findById(id)
                .map(flow -> getLastHistoryOrErrorResponse(flow))
                .orElseGet(() -> errorResponse(NOT_FOUND, "flow not found id: " + id));
    }

    private ResponseEntity getLastHistoryOrErrorResponse(Flow flow) {
        return historyService.findFirstByFluxoOrderByCreationDateTimeDesc(flow)
                .map(history -> ResponseEntity.ok(toHistoryResponse(history)))
                .orElseGet(() -> errorResponse(NOT_FOUND, "history not found for flow id: " + flow.getId()));
    }

    private HistoryResponse toHistoryResponse(History history) {
        return HistoryResponse.builder()
                .id(history.getId())
                .state(history.getState())
                .creationDateTime(history.getDateTime())
                .build();
    }

    @Operation(summary = "Get history list", description = "Get history list", tags = { "flow" })
    @StandardHistoryResponses
    @GetMapping("history/{id}/list")
    public ResponseEntity getHistoryListByFlow(@PathVariable String id) {
        return service.findById(id)
                .map(this::getHistoryListOrErrorResponse)
                .orElseGet(() -> errorResponse(NOT_FOUND, "flow not found id: " + id));
    }

    private ResponseEntity getHistoryListOrErrorResponse(Flow flow) {
        List<HistoryResponse> historyResponses = historyService.findByFluxoOrderByDateTimeDesc(flow)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toHistoryResponse)
                .collect(Collectors.toList());

        if (historyResponses.isEmpty()) {
            return errorResponse(NOT_FOUND, "no history found for flow id: " + flow.getId());
        }

        return ResponseEntity.ok(historyResponses);
    }

    @Operation(summary = "Change state", description = "Change state", tags = { "flow" })
    @StandardFlowResponses
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Not change state",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)) })
    })
    @PutMapping("{event}/{id}")
    public ResponseEntity changeState(@PathVariable String id, @PathVariable String event){
        log.info("change state");

        var flow = buildFlow(id, event);

        boolean eventSent = service.sendEvent(flow);

        log.info("sended event {}", eventSent);

        if (!eventSent) {
            String stateMachineError = getStateMachineError();
            if (stateMachineError != null) {
                return errorResponse(BAD_REQUEST, stateMachineError);
            }
        }

        return service.findById(flow.getId())
                .map(this::toFlowResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> errorResponse(NOT_FOUND, "flow not found id: " + flow.getId()));
    }

    private Flow buildFlow(String id, String event) {
        return Flow.builder()
                .id(id)
                .event(event)
                .dataType(StateMachineConfiguration.ACCEPT) // token validate guard condition
                .build();
    }

    private String getStateMachineError() {
        return (String) RequestContextHolder
                .getRequestAttributes()
                .getAttribute("stateMachineError", RequestAttributes.SCOPE_REQUEST);
    }

}