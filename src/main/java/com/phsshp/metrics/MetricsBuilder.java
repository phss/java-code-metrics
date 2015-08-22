package com.phsshp.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MetricsBuilder {

    private final List<Metrics> allMetrics;

    public MetricsBuilder() {
        allMetrics = new ArrayList<>();
    }

    public void add(File file, int value) {
        allMetrics.add(new Metrics(file, value));
    }

    public List<Metrics> build() {
        return allMetrics;
    }
}
