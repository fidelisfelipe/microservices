package com.example.microservices.currencyconversionservice.controller;

import com.example.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.example.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import com.example.microservices.currencyconversionservice.request.CurrencyTypeRequest;
import com.example.microservices.currencyconversionservice.response.CurrencyTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeServiceProxy proxy;
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable("from") String from,
                                                  @PathVariable("to") String to,
                                                  @PathVariable("quantity") BigDecimal quantity){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("quantity") BigDecimal quantity){

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
        logger.info("{}", response);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }

    @GetMapping("/currency-conversion-feign/type/list")
    public List<CurrencyTypeResponse> getExchangeTypes(){
        logger.info("getExhangeTypes called");
        return proxy.getCurrencyTypeList().getTypeList();
    }

    @DeleteMapping("/currency-conversion-feign/type/{id}")
    public ResponseEntity removeExchangeTypes(@PathVariable("id")Long id){
        logger.info("removeExchangeType called");
        proxy.removeExchangeType(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/currency-conversion-feign/type/{id}")
    public CurrencyTypeResponse getExchangeTypes(@PathVariable("id")Long id){
        logger.info("getExchangeType by id called {}", id);
        return proxy.getCurrencyType(id);
    }

    @PostMapping("/currency-conversion-feign/type")
    public CurrencyTypeResponse newTypes(@RequestBody CurrencyTypeRequest type){
        logger.info("new type by id called {}", type.getName());
        return proxy.newCurrencyType(type);
    }
    @PutMapping("/currency-conversion-feign/type")
    public ResponseEntity<CurrencyTypeResponse> updateType(@RequestBody CurrencyTypeRequest type){
        logger.info("update type by id called {}", type.getId());

        CurrencyTypeResponse typeResponse = proxy.updateType(type);

        return ResponseEntity.status(HttpStatus.OK).body(typeResponse);
    }
}
