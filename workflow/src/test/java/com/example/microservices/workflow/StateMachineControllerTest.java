package com.example.microservices.workflow;

import com.example.microservices.workflow.bean.FluxoStates;
import com.example.microservices.workflow.controller.StateMachineController;
import com.example.microservices.workflow.service.FluxoService;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

//referências assured: https://www.baeldung.com/rest-assured-tutorial
public class StateMachineControllerTest {

    @InjectMocks
    StateMachineController stateMachineController;
    @Mock
    FluxoService service;


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