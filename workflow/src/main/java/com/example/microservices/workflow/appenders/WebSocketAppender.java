package com.example.microservices.workflow.appenders;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.example.microservices.workflow.factorys.StaticWebContextAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {



    @Override
    protected void append(ILoggingEvent event) {
        SimpMessagingTemplate messagingTemplate = StaticWebContextAccessor.getMessagingTemplate();
        if (messagingTemplate != null) {
            String logMessage = event.getFormattedMessage();
            messagingTemplate.convertAndSend("/topic/logs", logMessage);
        }
    }
}