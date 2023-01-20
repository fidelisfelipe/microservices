package com.example.microservices.limitsservices.bean;

import org.springframework.beans.factory.annotation.Value;

public class LimitsConfiguration {

    private int maximum;

    private int minimum;

    protected LimitsConfiguration() {
    }
    public LimitsConfiguration(int maximum, int minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public int getMinimum() {
        return minimum;
    }

}
