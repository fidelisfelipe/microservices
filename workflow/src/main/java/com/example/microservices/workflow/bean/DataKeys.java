package com.example.microservices.workflow.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DataKeys {
    DATA_TYPE("dataType"),
    ID_FLUXO("idFluxo"),
    STATE("state");

    @Getter
    private String name;

}
