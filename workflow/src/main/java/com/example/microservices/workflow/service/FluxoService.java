package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Fluxo;
import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
import org.springframework.statemachine.StateMachine;

import java.util.Optional;

public interface FluxoService {
    Fluxo save(Fluxo entity);

    Optional<Fluxo> findById(Long id);
    StateMachine<FluxoStates, FluxoEvents> build(final Fluxo fluxo);

    boolean sendEvent(Fluxo fluxo);
}
