package com.example.microservices.workflow.repository;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StateMachineHistoryRepository extends JpaRepository<History, Long> {

    Optional<History> findFirstByFlowOrderByDateTimeDesc(Flow flow);

    Optional<List<History>> findByFlowOrderByDateTimeDesc(Flow flow);
}