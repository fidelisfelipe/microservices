package com.example.microservices.workflow.service;

import com.example.microservices.workflow.event.StateMachineErrorEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class StateMachineErrorServiceImpl implements StateMachineErrorService{
    @EventListener
    public void handleStateMachineError(StateMachineErrorEvent event) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        attributes.setAttribute("stateMachineError", event.message(), RequestAttributes.SCOPE_REQUEST);
    }
}
