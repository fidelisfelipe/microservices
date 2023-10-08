package com.example.microservices.workflow.interceptor;

import com.example.microservices.workflow.bean.*;
import com.example.microservices.workflow.event.StateMachineErrorEvent;
import com.example.microservices.workflow.exceptions.FlowNotFoundException;
import com.example.microservices.workflow.exceptions.MissingIdException;
import com.example.microservices.workflow.exceptions.UpdateFlowException;
import com.example.microservices.workflow.repository.StateMachineHistoryRepository;
import com.example.microservices.workflow.repository.StateMachineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class StateMachineInterceptor extends StateMachineInterceptorAdapter<FlowStates, FlowEvents> {
    private final StateMachineRepository stateMachineRepository;
    private final StateMachineHistoryRepository historyRepository;
    private final ApplicationEventPublisher eventPublisher;

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
        String id = extractIdFromMessage(message);

        if (id == null) {
            throw new MissingIdException("ID not found in message headers.");
        }

        updateStateMachineWithNewState(id, state);
    }

    private String extractIdFromMessage(Message<FlowEvents> message) {
        return String.class.cast(message.getHeaders().get(DataKeys.ID_FLOW.getName()));
    }

    private void updateStateMachineWithNewState(String id, State<FlowStates, FlowEvents> state) {
        Optional<Flow> flowOptional = stateMachineRepository.findById(id);

        if (!flowOptional.isPresent()) {
            throw new FlowNotFoundException(String.format("Flow with ID %s not found.", id));
        }

        Flow flow = flowOptional.get();
        flow.setState(state.getId().name());

        try {
            log.info("pre state change - save flow {}", flow);
            stateMachineRepository.save(flow);
            historyRepository.save(History.builder().flow(flow).state(flow.getState()).build());
        } catch (Exception e) {
            log.error("Failed to update and save flow.", e);
            throw new UpdateFlowException("Error updating and saving flow.", e);
        }
    }

    @Override
    public Exception stateMachineError(StateMachine<FlowStates, FlowEvents> stateMachine, Exception exception) {
        log.error("state machine error", exception);
        eventPublisher.publishEvent(StateMachineErrorEvent.builder()
                .message(exception.getMessage())
                .build());
        return exception;
    }
}
