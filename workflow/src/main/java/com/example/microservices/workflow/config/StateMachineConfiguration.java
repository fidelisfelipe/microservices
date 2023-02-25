package com.example.microservices.workflow.config;

import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
import com.example.microservices.workflow.listener.StateMachineListener;
import com.example.microservices.workflow.service.FluxoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.util.StringUtils;

@Slf4j
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class StateMachineConfiguration extends StateMachineConfigurerAdapter<FluxoStates, FluxoEvents> {

    private final StateMachineListener stateMachineListener;

    @Override
    public void configure(StateMachineConfigurationConfigurer<FluxoStates, FluxoEvents> config) throws Exception{
        config
                .withConfiguration()
                .listener(stateMachineListener);
    }

    @Override
    public void configure(StateMachineStateConfigurer<FluxoStates, FluxoEvents> states) throws Exception {
        states.withStates()
                .initial(FluxoStates.CRIADO, action())
                .state(FluxoStates.INICIADO, action(), endAction())
                .end(FluxoStates.FINALIZADO)
                .end(FluxoStates.CANCELADO);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<FluxoStates,
                FluxoEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(FluxoStates.CRIADO)
                .target(FluxoStates.INICIADO)
                .event(FluxoEvents.INICIAR)
                .guard(guard())
                .action(ctx -> {
                    log.info("INICIADO");
                })

                .and()
                .withExternal()
                .source(FluxoStates.CRIADO)
                .target(FluxoStates.CANCELADO)
                .event(FluxoEvents.CANCELAR)
                .action(ctx -> {
                    log.info("CANCELADO");
                })


                .and()
                .withExternal()
                .source(FluxoStates.INICIADO)
                .target(FluxoStates.CANCELADO)
                .event(FluxoEvents.CANCELAR)
                .action(ctx -> {
                    log.info("CANCELADO");
                })

                .and()
                .withExternal()
                .source(FluxoStates.INICIADO)
                .target(FluxoStates.FINALIZADO)
                .event(FluxoEvents.FINALIZAR)
                .action(ctx -> {
                    log.info("FINALIZADO");
                });
    }
    @Bean
    public Guard<FluxoStates, FluxoEvents> guard() {
        return ctx -> {
            log.info("guard condition");
            var dataType = String.class.cast(ctx.getExtendedState().getVariables().get(FluxoServiceImpl.DATA_TYPE));
            return !StringUtils.isEmpty(dataType) && dataType.equals("accept");
        };

    }

    @Bean
    public Action<FluxoStates, FluxoEvents> action() {
        return ctx -> log.info("action");
    }

    @Bean
    public Action<FluxoStates, FluxoEvents> endAction() {
        return ctx -> log.info("end action");
    }

    @Bean
    public Action<FluxoStates, FluxoEvents> errorAction() {
        return new Action<FluxoStates, FluxoEvents>() {
            @Override
            public void execute(StateContext<FluxoStates, FluxoEvents> ctx) {
                Exception exception = ctx.getException();
                log.error("error {}", exception.getMessage());
            }
        };
    }
}
