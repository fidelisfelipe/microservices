package com.example.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CurrencyExchangeController {
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to){
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);

        if(exchangeValue == null){
            throw new RuntimeException(String.format("Unable to find data for %s to %s", from, to));
        }

        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return exchangeValue;
    }
}
