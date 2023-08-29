package com.example.microservices.currencyexchangeservice.exception;

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
