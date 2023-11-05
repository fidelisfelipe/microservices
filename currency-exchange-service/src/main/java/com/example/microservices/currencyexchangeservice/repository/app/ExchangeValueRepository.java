package com.example.microservices.currencyexchangeservice.repository.app;

import com.example.microservices.currencyexchangeservice.model.app.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}
