package com.example.microservices.currencyexchangeservice;

import com.example.microservices.currencyexchangeservice.model.CurrencyType;
import com.example.microservices.currencyexchangeservice.model.ExchangeValue;
import com.example.microservices.currencyexchangeservice.repository.CurrencyTypeRepository;
import com.example.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;
    @Autowired
    private CurrencyTypeRepository currencyTypeRepository;
    @Autowired
    private Environment environment;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to){
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);

        if(exchangeValue == null){
            throw new RuntimeException(String.format("Unable to find data for %s to %s", from, to));
        }

        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return exchangeValue;
    }
    @GetMapping("/currency-exchange/type/list")
    public CurrencyResponse getCurrencyTypeList(){
        logger.info("getCurrencyTypeList called");
        var currencyTypeList = currencyTypeRepository.findAll();
        var currenseTypeResponseList = currencyTypeList.stream().map(currencyType -> {
            return CurrencyTypeResponse.builder()
                    .id(currencyType.getId())
                    .name(currencyType.getName())
                    .build();
        }).collect(Collectors.toList());
        var response = CurrencyResponse.builder().typeList(currenseTypeResponseList).build();
        return response;
    }
}
