package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
import org.springframework.statemachine.StateMachine;

import java.util.Optional;

public interface FlowService {
    Flow save(Flow entity);

    Optional<Flow> findById(String id);
    StateMachine<FlowStates, FlowEvents> build(final Flow flow);

    boolean sendEvent(Flow flow);
}
