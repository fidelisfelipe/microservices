package com.example.microservices.workflow.factorys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class StaticWebContextAccessor {
    private static SimpMessagingTemplate messagingTemplate;

    @Autowired
    public StaticWebContextAccessor(SimpMessagingTemplate messagingTemplate) {
        StaticWebContextAccessor.messagingTemplate = messagingTemplate;
    }

    public static SimpMessagingTemplate getMessagingTemplate() {
        return messagingTemplate;
    }
}
