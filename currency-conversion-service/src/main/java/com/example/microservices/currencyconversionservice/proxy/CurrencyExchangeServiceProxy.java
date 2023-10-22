package com.example.microservices.currencyconversionservice.proxy;

import com.example.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.example.microservices.currencyconversionservice.request.CurrencyTypeRequest;
import com.example.microservices.currencyconversionservice.response.CurrencyResponse;
import com.example.microservices.currencyconversionservice.response.CurrencyTypeResponse;
import com.example.microservices.currencyconversionservice.response.ExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
    @GetMapping("/currency-exchange/type/list")
    public CurrencyResponse getCurrencyTypeList();

    @GetMapping("/currency-exchange/exchange/list")
    public List<ExchangeResponse> getExchangeList();

    @DeleteMapping("/currency-exchange/type/{id}")
    public void removeExchangeType(@PathVariable("id") Long id);

    @GetMapping("/currency-exchange/type/{id}")
    CurrencyTypeResponse getCurrencyType(@PathVariable("id") Long id);

    @GetMapping("/currency-exchange/type/name/{name}")
    List<CurrencyTypeResponse> getCurrencyType(@PathVariable("name") String name);

    @PutMapping("/currency-exchange/type")
    CurrencyTypeResponse updateType(CurrencyTypeRequest type);

    @PostMapping("/currency-exchange/type")
    CurrencyTypeResponse newCurrencyType(CurrencyTypeRequest type);
}
