package com.example.microservices.currencyconversionservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;
}
