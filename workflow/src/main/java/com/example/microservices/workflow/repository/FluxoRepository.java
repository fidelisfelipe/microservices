package com.example.microservices.workflow.repository;

import com.example.microservices.workflow.bean.Fluxo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FluxoRepository extends JpaRepository<Fluxo, Long> {
}