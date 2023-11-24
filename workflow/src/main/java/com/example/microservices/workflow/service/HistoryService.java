package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.History;

import java.util.List;
import java.util.Optional;

public interface HistoryService {
    History save(History entity);

    Optional<History> findFirstByFluxoOrderByCreationDateTimeDesc(Flow fluxo);

    Optional<List<History>> findByFluxoOrderByDateTimeDesc(Flow fluxo);
}
