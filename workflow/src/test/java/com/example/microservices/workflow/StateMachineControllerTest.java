package com.example.microservices.workflow;

import com.example.microservices.workflow.bean.FlowStates;
import com.example.microservices.workflow.controller.StateMachineController;
import com.example.microservices.workflow.factorys.StateMachineFactory;
import com.example.microservices.workflow.service.FlowService;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateMachineControllerTest {

    @InjectMocks
    StateMachineController stateMachineController;
    @Mock
    FlowService service;


    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKeyAndValue_thenCorrect() throws Exception {
        post("http://localhost:8200/api/v1/flow/create").then().statusCode(200).assertThat()
                .body("state", equalTo(FlowStates.CRIADO.name()));
    }

    @Test
    public void whenRequestGet_thenOK(){
        when().request("POST", "http://localhost:8200/api/v1/flow/create").then().statusCode(200);
    }

    @Test
    public void whenMeasureResponseTime_thenOK() {
        Response response = get("http://localhost:8200/api/v1/flow/create");
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        assertEquals(timeInS, timeInMS/1000);
    }

    @Test
    public void whenValidateResponseTime_thenSuccess() {
        when().get("http://localhost:8200/api/v1/flow/create").then().time(lessThan(5000L));
    }

    @Test
    public void whenValidateResponseTimeInSeconds_thenSuccess(){
        when().get("http://localhost:8200/api/v1/flow/create").then().time(lessThan(5L),TimeUnit.SECONDS);
    }


}