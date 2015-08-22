package com.phsshp.metrics;

public class Measurement {

    private final MetricType type;
    private final int value;

    public Measurement(MetricType type, int value) {
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
