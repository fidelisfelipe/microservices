package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.History;
import com.example.microservices.workflow.repository.StateMachineHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final StateMachineHistoryRepository repository;


    @Override
    public History save(History entity){
        return repository.save(entity);
    }

    @Override
    public Optional<History> findFirstByFluxoOrderByCreationDateTimeDesc(Flow flow) {
        return repository.findFirstByFlowOrderByDateTimeDesc(flow);
    }

    @Override
    public Optional<List<History>> findByFluxoOrderByDateTimeDesc(Flow flow) {
        return repository.findByFlowOrderByDateTimeDesc(flow);
    }


}
