package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Flow;
import com.example.microservices.workflow.bean.FlowEvents;
import com.example.microservices.workflow.bean.FlowStates;
import com.example.microservices.workflow.interceptor.StateMachineInterceptor;
import com.example.microservices.workflow.repository.StateMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FluxoServiceImpl implements FlowService {
    public static final String DATA_TYPE = "dataType";
    public static final String ID_FLOW = "idFluxo";
    public static final String STATE = "state";

    private final StateMachineRepository repository;
    private final StateMachineFactory<FlowStates, FlowEvents> stateMachineFactory;

    private final StateMachineInterceptor stateMachineInterceptor;

    @Override
    public Flow save(Flow entity){
        return repository.save(entity);
    }

    @Override
    public Optional<Flow> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean sendEvent(Flow flow){
        StateMachine<FlowStates, FlowEvents> sm = build(flow);
        //set data for guard verify
        sm.getExtendedState().getVariables().put(FluxoServiceImpl.DATA_TYPE,flow.getDataType());
        //set event
        var msg = MessageBuilder.withPayload(FlowEvents.valueOf(flow.getEvent()))
                //set headers
                .setHeader(FluxoServiceImpl.ID_FLOW,flow.getId())
                .setHeader(FluxoServiceImpl.STATE,flow.getState())
                .build();

        return sm.sendEvent(msg);
    }

    @Override
    public StateMachine<FlowStates, FlowEvents> build(final Flow flow){
        var entity =  this.repository.findById(flow.getId());
        var stateMachine =  this.stateMachineFactory.getStateMachine(flow.getId().toString());
        stateMachine.stop();
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(stateMachineInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(FlowStates.valueOf(entity.get().getState()), null, null, null));
                });
        stateMachine.start();
        return stateMachine;
    }
}
