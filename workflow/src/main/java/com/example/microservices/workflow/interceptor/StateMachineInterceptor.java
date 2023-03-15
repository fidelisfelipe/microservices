package com.example.microservices.workflow.interceptor;

import com.example.microservices.workflow.bean.DataKeys;
import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
import com.example.microservices.workflow.bean.History;
import com.example.microservices.workflow.repository.StateMachineHistoryRepository;
import com.example.microservices.workflow.repository.StateMachineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StateMachineInterceptor extends StateMachineInterceptorAdapter<FluxoStates, FluxoEvents> {
    private final StateMachineRepository repository;
    private final StateMachineHistoryRepository historyRepository;
    @Override
    public void preStateChange( State<FluxoStates,
                                    FluxoEvents> state,
                                Message<FluxoEvents> message,
                                Transition<FluxoStates,
                                FluxoEvents> transition,
                                StateMachine<FluxoStates,
                                FluxoEvents> stateMachine,
                                StateMachine<FluxoStates,
                                        FluxoEvents> rootStateMachine) {
        log.info("pre state change");
        var fluxoId = Long.class.cast(message.getHeaders().get(DataKeys.ID_FLUXO.getName()));
        var fluxo =  repository.findById(fluxoId);
        if(fluxo.isPresent()){
            fluxo.get().setState(state.getId().name());
            fluxo.get().setDataType(String.valueOf(message.getHeaders().get(DataKeys.ID_FLUXO.getName())));
            log.info("pre state change - save fluxo {}", fluxo);
            repository.save(fluxo.get());
            historyRepository.save(History.builder().fluxo(fluxo.get()).state(fluxo.get().getState()).build());
        }
    }
    @Override
    public Exception stateMachineError(StateMachine<FluxoStates, FluxoEvents> stateMachine, Exception exception) {
        log.error("state machine erro",exception);
        return exception;
    }
}
