package com.phsshp.metrics.model;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<MetricType, Aggregation> defaultAggregationsPerMetric() {
        HashMap<MetricType, Aggregation> aggregationPerMetric = new HashMap<>();
        for (MetricType metric : MetricType.values()) {
            aggregationPerMetric.put(metric, metric.getDefaultAggregation());
        }
        return aggregationPerMetric;
    }
}
