package com.example.microservices.workflow.response;

import lombok.Builder;

@Builder
public record MessageResponse(int status, String message){
}
