package com.example.microservices.currencyconversionservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Builder
public class CurrencyTypeResponse {
    private Long id;
    private String name;
}
