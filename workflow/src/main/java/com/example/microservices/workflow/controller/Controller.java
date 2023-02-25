package com.example.microservices.workflow.controller;

import com.example.microservices.workflow.bean.Fluxo;
import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
import com.example.microservices.workflow.interceptor.StateMachineInterceptor;
import com.example.microservices.workflow.repository.FluxoRepository;
import com.example.microservices.workflow.service.FluxoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

    private final FluxoRepository repository;
    private final StateMachineFactory<FluxoStates, FluxoEvents> stateMachineFactory;

    private final StateMachineInterceptor stateMachineInterceptor;

    @GetMapping("state/{id}")
    public ResponseEntity state(@PathVariable Long id){
        log.info("id: {}", id);

        Optional<Fluxo> fluxo = repository.findById(id);

        if(fluxo.isPresent())
            return ResponseEntity.status(CREATED).body(fluxo);

            return ResponseEntity.status(NOT_FOUND).body(String.format("not found id:%s", id));
    }
    @GetMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Fluxo create(){
        Fluxo fluxo = new Fluxo();
        fluxo.setState(FluxoStates.CRIADO.name());
        fluxo.setLocalDate(LocalDate.now());
        return repository.save(fluxo);
    }

    @PutMapping("change")
    @ResponseStatus(HttpStatus.CREATED)
    public String changeState(@RequestBody Fluxo fluxo){
        StateMachine<FluxoStates, FluxoEvents> sm = build(fluxo);
        sm.getExtendedState().getVariables().put(FluxoServiceImpl.DATA_TYPE,fluxo.getDataType());
        var msg = MessageBuilder.withPayload(FluxoEvents.valueOf(fluxo.getEvent()))
                .setHeader(FluxoServiceImpl.ID_FLUXO,fluxo.getId())
                .setHeader(FluxoServiceImpl.STATE,fluxo.getState())
                .build();

        sm.sendEvent(msg);
        return "state changed";
    }

    private StateMachine<FluxoStates, FluxoEvents> build(final Fluxo fluxo){
        var entity =  this.repository.findById(fluxo.getId());
        var stateMachine =  this.stateMachineFactory.getStateMachine(fluxo.getId().toString());
        stateMachine.stop();
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(stateMachineInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(FluxoStates.valueOf(entity.get().getState()), null, null, null));
                });
        stateMachine.start();
        return stateMachine;
    }

}
