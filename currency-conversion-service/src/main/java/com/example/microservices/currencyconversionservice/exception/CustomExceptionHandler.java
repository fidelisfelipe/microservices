package com.example.microservices.currencyconversionservice.exception;

import com.example.microservices.currencyconversionservice.msg.MessageManager;
import com.example.microservices.currencyconversionservice.msg.MessageReceiver;
import com.example.microservices.currencyconversionservice.msg.MessageSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.TimeUnit;
@Log4j2
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {



    @Autowired
    private MessageReceiver messageReceiver;

    @Autowired
    private MessageSender messageSender;


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError("Conflict");
        errorResponse.setMessage("Erro de integridade de dados.");

        // Obter o HttpServletRequest atual usando RequestContextHolder
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            String requestPath = attributes.getRequest().getRequestURI();
            errorResponse.setPath(requestPath);
        }

        messageSender.sendMessage(errorResponse.getMessage());

        // Retorne a resposta em JSON com o status e a mensagem
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

    }
}
