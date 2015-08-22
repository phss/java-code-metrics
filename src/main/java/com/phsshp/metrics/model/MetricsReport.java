package com.phsshp.metrics.model;

import java.util.List;

public class MetricsReport {

    private final List<FileMeasurements> measurements;

    public MetricsReport(List<FileMeasurements> measurements) {
        this.measurements = measurements;
    }

    public List<FileMeasurements> getMeasurements() {
        return measurements;
    }
}
