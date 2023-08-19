package com.example.microservices.currencyexchangeservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@Builder
public class CurrencyResponse {
    private List<CurrencyTypeResponse> typeList;
}