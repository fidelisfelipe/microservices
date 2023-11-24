package com.example.microservices.workflow.exceptions;

public class UpdateFlowException extends RuntimeException{
    public UpdateFlowException(String message, Throwable cause) {
        super(message, cause);
    }
}
