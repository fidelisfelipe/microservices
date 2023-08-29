package com.example.microservices.currencyconversionservice.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static feign.FeignException.errorStatus;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == HttpStatus.CONFLICT.value()) {
            // Lançar uma exceção personalizada para esse caso
            return new DataIntegrityViolationException("Erro de integridade de dados.");
        }
        // Lidar com outros códigos de erro aqui
        // ...
        return errorStatus(methodKey, response);
    }
}






