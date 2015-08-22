package com.phsshp.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MetricsBuilder {

    private final List<Metrics> allMetrics;

    public MetricsBuilder() {
        allMetrics = new ArrayList<>();
    }

    public void add(File file, Measure measure) {
        allMetrics.add(new Metrics(file, measure));
    }

    public List<Metrics> build() {
        return allMetrics;
    }
}
