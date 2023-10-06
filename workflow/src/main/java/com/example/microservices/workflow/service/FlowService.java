package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
import org.springframework.statemachine.StateMachine;

import java.util.Optional;
import java.util.UUID;

public interface FluxoService {
    Flow save(Flow entity);

    Optional<Flow> findById(UUID id);
    StateMachine<FluxoStates, FluxoEvents> build(final Flow flow);

    boolean sendEvent(Flow flow);
}
