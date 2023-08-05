package com.example.microservices.workflow.service;

import com.example.microservices.workflow.bean.Fluxo;
import com.example.microservices.workflow.bean.FluxoEvents;
import com.example.microservices.workflow.bean.FluxoStates;
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
public class FluxoServiceImpl implements FluxoService {
    public static final String DATA_TYPE = "dataType";
    public static final String ID_FLUXO = "idFluxo";
    public static final String STATE = "state";

    private final StateMachineRepository repository;
    private final StateMachineFactory<FluxoStates, FluxoEvents> stateMachineFactory;

    private final StateMachineInterceptor stateMachineInterceptor;

    @Override
    public Fluxo save(Fluxo entity){
        return repository.save(entity);
    }

    @Override
    public Optional<Fluxo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean sendEvent(Fluxo fluxo){
        StateMachine<FluxoStates, FluxoEvents> sm = build(fluxo);
        //set data for guard verify
        sm.getExtendedState().getVariables().put(FluxoServiceImpl.DATA_TYPE,fluxo.getDataType());
        //set event
        var msg = MessageBuilder.withPayload(FluxoEvents.valueOf(fluxo.getEvent()))
                //set headers
                .setHeader(FluxoServiceImpl.ID_FLUXO,fluxo.getId())
                .setHeader(FluxoServiceImpl.STATE,fluxo.getState())
                .build();

        return sm.sendEvent(msg);
    }

    @Override
    public StateMachine<FluxoStates, FluxoEvents> build(final Fluxo fluxo){
        var entity =  this.repository.findById(fluxo.getId());
        var stateMachine =  this.stateMachineFactory.getStateMachine(fluxo.getId().toString());
        stateMachine.stop();
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(stateMachineInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(FluxoStates.valueOf(entity.get().getState()), null, null, null));
                });
        stateMachine.start();
        return stateMachine;
    }
}
