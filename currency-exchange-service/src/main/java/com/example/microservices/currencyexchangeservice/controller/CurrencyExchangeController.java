package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.model.CurrencyType;
import com.example.microservices.currencyexchangeservice.model.ExchangeValue;
import com.example.microservices.currencyexchangeservice.repository.CurrencyTypeRepository;
import com.example.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import com.example.microservices.currencyexchangeservice.request.CurrencyTypeRequest;
import com.example.microservices.currencyexchangeservice.response.CurrencyResponse;
import com.example.microservices.currencyexchangeservice.response.CurrencyTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


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
    @DeleteMapping("/currency-exchange/type/{id}")
    public void removeExchangeType(@PathVariable("id") Long id){
        logger.info("removeExchangeType called");
        currencyTypeRepository.deleteById(id);
    }
    @GetMapping("/currency-exchange/type/{id}")
    public CurrencyTypeResponse getExchangeType(@PathVariable("id") Long id){
        logger.info("getExchangeType called");
        var type = currencyTypeRepository.findById(id);
        var typeReturn = CurrencyTypeResponse.builder().id(type.get().getId()).name(type.get().getName()).build();
        return typeReturn;
    }
    @PutMapping("/currency-exchange/type")
    public void updateType(@RequestBody CurrencyTypeRequest typeRequest){
        logger.info("updateType called");
        var type = currencyTypeRepository.findById(typeRequest.getId());
        if(!type.isPresent()){
            throw new RuntimeException("Type requested does not exists");
        }
        var typeUpdate = CurrencyType.builder().id(type.get().getId()).name(typeRequest.getName()).build();

        currencyTypeRepository.save(typeUpdate);
    }

    @PostMapping("/currency-exchange/type")
    public CurrencyTypeResponse newType(@RequestBody CurrencyTypeRequest typeRequest){
        logger.info("newType called");
        var type = currencyTypeRepository.findByName(typeRequest.getName());
        if(type != null){
            throw new RuntimeException(String.format("Type %s already exists", type.getName()));
        }
        var entity = CurrencyType.builder().name(typeRequest.getName()).build();
        currencyTypeRepository.save(entity);
        var typeReturn = CurrencyTypeResponse.builder().id(entity.getId()).name(entity.getName()).build();
        return typeReturn;
    }
}
