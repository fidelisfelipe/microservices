package com.example.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
    @GetMapping("/currency-exchange/type/list")
    public CurrencyResponse getCurrencyTypeList();

    @DeleteMapping("/currency-exchange/type/{id}")
    public void removeExchangeType(@PathVariable("id") Long id);

    @GetMapping("/currency-exchange/type/{id}")
    CurrencyTypeResponse getCurrencyType(@PathVariable("id") Long id);

    @PutMapping("/currency-exchange/type")
    CurrencyTypeResponse updateType(CurrencyTypeRequest type);

    @PostMapping("/currency-exchange/type")
    CurrencyTypeResponse newCurrencyType(CurrencyTypeRequest type);
}
