package com.example.microservices.workflow;

import com.example.microservices.workflow.controller.StateMachineController;
import com.example.microservices.workflow.service.FlowService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

//referências assured: https://www.baeldung.com/rest-assured-tutorial
public class StateMachineControllerTest {

    @InjectMocks
    StateMachineController stateMachineController;
    @Mock
    FlowService service;


    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKeyAndValue_thenCorrect() {
        //get("http://localhost:8200/create").then().statusCode(201).assertThat()
        //        .body("state", equalTo(FluxoStates.CRIADO.name()));
    }

    @Test
    public void whenRequestGet_thenOK(){
        //when().request("GET", "http://localhost:8200/create").then().statusCode(201);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {
        //Response response = RestAssured.get("http://localhost:8200/create");
        //long timeInMS = response.time();
        //long timeInS = response.timeIn(TimeUnit.SECONDS);

        //assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        //when().get("http://localhost:8200/create").then().time(lessThan(5000L));
    }

    @Test
    public void whenValidateResponseTimeInSeconds_thenSuccess(){
        //when().get("http://localhost:8200/create").then().time(lessThan(5L),TimeUnit.SECONDS);
    }


}