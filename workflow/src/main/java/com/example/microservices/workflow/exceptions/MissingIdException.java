package com.example.microservices.workflow.exceptions;

public class MissingIdException extends RuntimeException{
    public MissingIdException(String message) {
        super(message);
    }
}
