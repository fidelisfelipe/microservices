package com.example.microservices.currencyconversionservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Builder
public class CurrencyTypeRequest {
    private Long id;
    private String name;
}
