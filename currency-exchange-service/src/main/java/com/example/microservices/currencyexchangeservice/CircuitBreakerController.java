package com.example.microservices.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public ResponseEntity<String> sampleApi(){
        logger.info("Sample api call");
        var forEntity = new RestTemplate().getForEntity("http://8080/some-dummy-url", String.class);
        return forEntity;
    }
    public ResponseEntity<String> hardcodedResponse(Exception exception){
        logger.error("fallback-response : {}", exception.getMessage());
        return ResponseEntity.badRequest().body("fallback-response");
    }
}
