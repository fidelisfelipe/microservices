package com.example.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "default")//10s -> 10000 calls to the sample api
    @Bulkhead(name = "default")
    public ResponseEntity<String> sampleApi(){
        logger.info("Sample api call");
        //var forEntity = new RestTemplate().getForEntity("http://8080/some-dummy-url", String.class);
        return ResponseEntity.ok().build();
    }
    public ResponseEntity<String> hardcodedResponse(Exception exception){
        logger.error("fallback-response : {}", exception.getMessage());
        return ResponseEntity.badRequest().body("fallback-response");
    }
}
