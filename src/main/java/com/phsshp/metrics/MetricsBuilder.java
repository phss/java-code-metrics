package com.phsshp.metrics;

import java.util.ArrayList;
import java.util.List;

public class MetricsBuilder {

    private final List<Metrics> allMetrics;

    public MetricsBuilder() {
        allMetrics = new ArrayList<>();
    }

    public void add(Metrics metrics) {
        allMetrics.add(metrics);
    }

    public List<Metrics> build() {
        return allMetrics;
    }
}
