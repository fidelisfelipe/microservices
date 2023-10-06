package com.example.microservices.workflow.repository;

import com.example.microservices.workflow.bean.Flow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface StateMachineRepository extends JpaRepository<Flow, String> {
}