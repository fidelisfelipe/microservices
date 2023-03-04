package com.example.microservices.workflow.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DataGuards {
    DATA_ACCEPT("accept");

    @Getter
    private String name;
}
