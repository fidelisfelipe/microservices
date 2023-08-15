package com.example.microservices.workflow;

import com.example.microservices.workflow.bean.FluxoStates;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
//Personalizando Matchers
public class IsFluxoStatesFinalizado extends TypeSafeMatcher<FluxoStates> {

    public void describeTo(Description description) {
        description.appendText("a fluxo state equals finalizado");
    }

    @Factory
    public static Matcher<FluxoStates> isFluxoStatesFinalizado() {
        return new IsFluxoStatesFinalizado();
    }

    @Override
    protected boolean matchesSafely(FluxoStates state) {
        return state.equals(FluxoStates.FINALIZADO);
    }

}