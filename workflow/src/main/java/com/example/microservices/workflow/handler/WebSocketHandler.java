package com.example.microservices.workflow.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        // Handle incoming messages here
        String receivedMessage = (String) message.getPayload();
        // Process the message and send a response if needed
        session.sendMessage(new TextMessage("Received: " + receivedMessage));
        log.info("Received: " + receivedMessage);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
    // Perform actions when a new WebSocket connection is established
        log.info("Connection established");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    // Perform actions when a WebSocket connection is closed
        log.info("Connection closed");
    }
}
