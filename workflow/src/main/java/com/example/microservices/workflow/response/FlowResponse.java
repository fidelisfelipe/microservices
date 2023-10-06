package com.example.microservices.workflow.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FlowResponse(String id, String state, LocalDateTime dataTime) {
}
