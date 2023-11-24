package com.example.microservices.workflow.factorys;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;
import org.springframework.statemachine.config.configurers.StateConfigurer;

import static org.springframework.statemachine.config.StateMachineBuilder.builder;

public class StateMachineFactory {
    public StateMachine<String, String> buildMachine(String name) throws Exception {
        Builder<String, String> construtor = builder();
        construtor.configureConfiguration().withConfiguration().machineId(name);
        construtor.configureStates().withStates()
                .initial("SI").state("S1").state("S2").state("S3").state("SF")
                .history("SH", StateConfigurer.History.SHALLOW);

        construtor.configureTransitions()
                .withExternal()
                .source("SI").target("S1").event("E1")
                .and()
                .withExternal()
                .source("S1").target("S2").event("E2")
                .and()
                .withExternal()
                .source("S2").target("S3").event("E3")
                .and()
                .withExternal()
                .source("S3").target("SF").event("E4");

        construtor.configureConfiguration().withConfiguration().autoStartup(true);

        return construtor.build();
    }
}
