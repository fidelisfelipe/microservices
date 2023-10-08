package com.example.microservices.workflow.config;

import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
import com.example.microservices.workflow.listener.StateMachineListener;
import com.example.microservices.workflow.service.FlowServiceImpl;
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
public class StateMachineConfiguration extends StateMachineConfigurerAdapter<FlowStates, FlowEvents> {

    public static final String ACCEPT = "accept";
    private final StateMachineListener stateMachineListener;

    @Override
    public void configure(StateMachineConfigurationConfigurer<FlowStates, FlowEvents> config) throws Exception{
        config
                .withConfiguration()
                .listener(stateMachineListener);

    }

    @Override
    public void configure(StateMachineStateConfigurer<FlowStates, FlowEvents> states) throws Exception {
        states.withStates()
                .initial(FlowStates.CRIADO, action())
                .state(FlowStates.INICIADO, action(), endAction())
                .end(FlowStates.FINALIZADO)
                .end(FlowStates.CANCELADO);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<FlowStates,
            FlowEvents> transitions) throws Exception {
        transitions

                .withExternal()
                .source(FlowStates.CRIADO)
                .target(FlowStates.CRIADO)
                .event(FlowEvents.CRIAR)
                .guard(guard())
                .action(ctx -> {
                    log.info("CRIADO");
                })

                .and()
                .withExternal()
                .source(FlowStates.CRIADO)
                .target(FlowStates.INICIADO)
                .event(FlowEvents.INICIAR)
                .guard(guard())
                .action(ctx -> {
                    log.info("INICIADO");
                })

                .and()
                .withExternal()
                .source(FlowStates.CRIADO)
                .target(FlowStates.CANCELADO)
                .event(FlowEvents.CANCELAR)
                .action(ctx -> {
                    log.info("CANCELADO");
                })


                .and()
                .withExternal()
                .source(FlowStates.INICIADO)
                .target(FlowStates.CANCELADO)
                .event(FlowEvents.CANCELAR)
                .action(ctx -> {
                    log.info("CANCELADO");
                })

                .and()
                .withExternal()
                .source(FlowStates.INICIADO)
                .target(FlowStates.FINALIZADO)
                .event(FlowEvents.FINALIZAR)
                .action(ctx -> {
                    log.info("FINALIZADO");
                });
    }
    @Bean
    public Guard<FlowStates, FlowEvents> guard() {
        return ctx -> {
            log.info("guard condition");
            var dataType = String.class.cast(ctx.getExtendedState().getVariables().get(FlowServiceImpl.DATA_TYPE));
            return !StringUtils.isEmpty(dataType) && dataType.equals(ACCEPT);
        };

    }

    @Bean
    public Action<FlowStates, FlowEvents> action() {
        return ctx -> log.info("action");
    }

    @Bean
    public Action<FlowStates, FlowEvents> endAction() {
        return ctx -> log.info("end action");
    }

    @Bean
    public Action<FlowStates, FlowEvents> errorAction() {
        return new Action<FlowStates, FlowEvents>() {
            @Override
            public void execute(StateContext<FlowStates, FlowEvents> ctx) {
                Exception exception = ctx.getException();
                log.error("error action {}", exception.getMessage());
            }
        };
    }
}
