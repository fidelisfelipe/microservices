package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Fluxo;
import com.example.microservices.workflow.bean.History;

import java.util.Optional;

public interface HistoryService {
    History save(History entity);

    Optional<History> findFirstByFluxoOrderByCreationDateDesc(Fluxo fluxo);
}
