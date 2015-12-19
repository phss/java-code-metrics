package com.phsshp.metrics.model;

public enum MetricType {
    FILE_SIZE(Aggregation.FIRST),
    CYCLOMATIC_COMPLEXITY(Aggregation.SUM),
    FANOUT_COMPLEXITY(Aggregation.FIRST);

    private final Aggregation defaultAggregation;

    MetricType(Aggregation defaultAggregation) {
        this.defaultAggregation = defaultAggregation;
    }

    public Aggregation getDefaultAggregation() {
        return defaultAggregation;
    }
}
