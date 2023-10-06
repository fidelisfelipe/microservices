package com.example.microservices.workflow.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record HistoryResponse(Long id, FlowResponse flow, String state, LocalDateTime creationDateTime) {
}
