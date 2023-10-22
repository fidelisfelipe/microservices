package com.example.microservices.currencyexchangeservice.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ExchangeResponse(Long id, String from, String to, BigDecimal rate, int port){
}
