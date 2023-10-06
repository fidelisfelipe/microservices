package com.example.microservices.workflow;

import com.example.microservices.workflow.bean.FlowStates;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
//Personalizando Matchers
public class IsFluxoStatesFinalizado extends TypeSafeMatcher<FlowStates> {

    public void describeTo(Description description) {
        description.appendText("a fluxo state equals finalizado");
    }

    @Factory
    public static Matcher<FlowStates> isFluxoStatesFinalizado() {
        return new IsFluxoStatesFinalizado();
    }

    @Override
    protected boolean matchesSafely(FlowStates state) {
        return state.equals(FlowStates.FINALIZADO);
    }

}