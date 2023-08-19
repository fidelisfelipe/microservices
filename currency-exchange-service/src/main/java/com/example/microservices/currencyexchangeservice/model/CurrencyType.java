package com.example.microservices.currencyexchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currency_type")
public class CurrencyType {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
}
