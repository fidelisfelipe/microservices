package com.example.microservices.currencyexchangeservice.repository;

import com.example.microservices.currencyexchangeservice.model.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {
    CurrencyType findByName(String name);

    List<CurrencyType> findByNameContaining(String name);
}
