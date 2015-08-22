package com.phsshp.metrics;

public class Measure {

    private final MetricType type;
    private final int value;

    public Measure(MetricType type, int value) {
        this.type = type;
        this.value = value;
    }

    public MetricType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
