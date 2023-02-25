package com.example.microservices.workflow.controller;

import com.example.microservices.workflow.bean.Fluxo;
import com.example.microservices.workflow.bean.FluxoStates;
import com.example.microservices.workflow.service.FluxoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StateMachineController {

    private final FluxoService service;

    @GetMapping("state/{id}")
    public ResponseEntity state(@PathVariable Long id){
        log.info("get state");
        Optional<Fluxo> fluxo = service.findById(id);

        if(fluxo.isPresent())
            return ResponseEntity.status(CREATED).body(fluxo);

            return ResponseEntity.status(NOT_FOUND).body(String.format("not found id:%s", id));
    }
    @GetMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(){
        log.info("create");
        var fluxo = Fluxo.builder()
                .state(FluxoStates.CRIADO.name())
                .localDate(LocalDate.now()).build();

        return ResponseEntity.status(CREATED).body(service.save(fluxo));
    }

    @PutMapping("change")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity changeState(@RequestBody Fluxo fluxo){
        log.info("change state");
        service.sendEvent(fluxo);
        return ResponseEntity.status(CREATED).body(service.findById(fluxo.getId()));
    }

}
