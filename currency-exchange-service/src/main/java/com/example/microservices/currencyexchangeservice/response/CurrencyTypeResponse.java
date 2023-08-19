package com.example.microservices.currencyexchangeservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CurrencyTypeResponse {
    private Long id;
    private String name;
}
