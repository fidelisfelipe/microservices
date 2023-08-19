package com.example.microservices.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
}
