package com.example.microservices.workflow.listener;

import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
import com.example.microservices.workflow.event.StateMachineErrorEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class StateMachineListener extends StateMachineListenerAdapter<FlowStates, FlowEvents> {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void stateChanged(State<FlowStates, FlowEvents> from,
                             State<FlowStates, FlowEvents> to){
        log.info(String.format("State changed from : %s, to: %s", from, to));
    }
    @Override
    public void transition(Transition<FlowStates, FlowEvents> transition) {
        log.warn("transition from:{} to:{}",
                ofNullableState(transition.getSource()),
                ofNullableState(transition.getTarget()));
    }
    private Object ofNullableState(State s) {
        return Optional.ofNullable(s)
                .map(State::getId)
                .orElse(null);
    }
    @Override
    public void eventNotAccepted(Message<FlowEvents> event) {
        log.error("event not accepted: {}", event);
        eventPublisher.publishEvent(StateMachineErrorEvent.builder()
                .message("invalid event: "+event.getPayload().name())
                .build());
    }

}
