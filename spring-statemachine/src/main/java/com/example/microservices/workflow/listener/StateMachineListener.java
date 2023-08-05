package com.example.microservices.workflow.listener;

import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class StateMachineListener extends StateMachineListenerAdapter<FluxoStates, FluxoEvents> {
    @Override
    public void stateChanged(State<FluxoStates, FluxoEvents> from,
                             State<FluxoStates, FluxoEvents> to){
        log.info(String.format("State changed from : %s, to: %s", from, to));
    }
    @Override
    public void transition(Transition<FluxoStates, FluxoEvents> transition) {
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
    public void eventNotAccepted(Message<FluxoEvents> event) {
        log.error("event not accepted: {}", event);
    }

}
