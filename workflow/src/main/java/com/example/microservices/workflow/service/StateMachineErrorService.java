package com.example.microservices.workflow.service;

import com.example.microservices.workflow.event.StateMachineErrorEvent;

public interface StateMachineErrorService {
    void handleStateMachineError(StateMachineErrorEvent event);
}
