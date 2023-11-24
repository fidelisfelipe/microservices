package com.example.microservices.workflow.exceptions;

public class FlowNotFoundException extends RuntimeException{
    public FlowNotFoundException(String message) {
        super(message);
    }
}
