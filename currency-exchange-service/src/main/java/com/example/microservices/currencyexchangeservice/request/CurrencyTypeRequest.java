package com.example.microservices.currencyexchangeservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CurrencyTypeRequest {
    private Long id;
    private String name;
}
