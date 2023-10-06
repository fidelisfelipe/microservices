package com.example.microservices.workflow.event;

import lombok.Builder;

@Builder
public record StateMachineErrorEvent(int status, String message) {
}
