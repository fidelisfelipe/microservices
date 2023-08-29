package com.example.microservices.currencyconversionservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Builder
public class CurrencyResponse {
    private List<CurrencyTypeResponse> typeList;
}
