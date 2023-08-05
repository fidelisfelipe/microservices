package com.example.microservices.workflow.repository;

import com.example.microservices.workflow.bean.Fluxo;
import com.example.microservices.workflow.bean.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StateMachineHistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findFirstByFluxoOrderByCreationDateDesc(Fluxo fluxo);
}