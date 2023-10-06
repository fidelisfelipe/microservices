package com.example.microservices.workflow.interceptor;

import com.example.microservices.workflow.bean.DataKeys;
import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
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

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StateMachineInterceptor extends StateMachineInterceptorAdapter<FlowStates, FlowEvents> {
    private final StateMachineRepository repository;
    private final StateMachineHistoryRepository historyRepository;
    @Override
    public void preStateChange( State<FlowStates,
            FlowEvents> state,
                                Message<FlowEvents> message,
                                Transition<FlowStates,
                                        FlowEvents> transition,
                                StateMachine<FlowStates,
                                        FlowEvents> stateMachine,
                                StateMachine<FlowStates,
                                        FlowEvents> rootStateMachine) {
        log.info("pre state change");
        var id = String.class.cast(message.getHeaders().get(DataKeys.ID_FLOW.getName()));
        var flow =  repository.findById(id);
        if(flow.isPresent()){
            flow.get().setState(state.getId().name());

            log.info("pre state change - save flow {}", flow);
            repository.save(flow.get());
            historyRepository.save(History.builder().flow(flow.get()).state(flow.get().getState()).build());
        }
    }
    @Override
    public Exception stateMachineError(StateMachine<FlowStates, FlowEvents> stateMachine, Exception exception) {
        log.error("state machine erro",exception);
        return exception;
    }
}
