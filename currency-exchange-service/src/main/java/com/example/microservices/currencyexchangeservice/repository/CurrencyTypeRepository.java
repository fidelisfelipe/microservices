package com.example.microservices.currencyexchangeservice.repository;

import com.example.microservices.currencyexchangeservice.model.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {
    CurrencyType findByName(String name);
}
