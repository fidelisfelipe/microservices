package com.example.microservices.currencyexchangeservice.repository.aud;

import com.example.microservices.currencyexchangeservice.model.aud.HistoryConversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryConversionRepository extends JpaRepository<HistoryConversion, Long> {

}
