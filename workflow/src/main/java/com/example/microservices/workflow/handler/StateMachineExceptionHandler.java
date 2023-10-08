package com.example.microservices.workflow.handler;

import com.example.microservices.workflow.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@ControllerAdvice
public class StateMachineExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageResponse.builder().status(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageResponse> handleGeneralException(Exception ex) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        String errorMessage = (String) attributes.getAttribute("stateMachineError", RequestAttributes.SCOPE_REQUEST);

        if (errorMessage != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(errorMessage).build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).build());
        }
    }

}
