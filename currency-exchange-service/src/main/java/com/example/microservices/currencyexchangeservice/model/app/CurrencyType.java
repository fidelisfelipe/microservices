package com.example.microservices.currencyexchangeservice.model.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "currency_type")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class CurrencyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
