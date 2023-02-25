package com.example.microservices.workflow.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Fluxo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate localDate;
    private String state;

    @Transient
    String event;

    @Transient
    String dataType;
}
