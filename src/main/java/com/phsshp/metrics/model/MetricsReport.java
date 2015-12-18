package com.phsshp.metrics.model;

import java.util.Collection;

public class MetricsReport {

    private final Collection<FileMeasurements> measurements;

    public MetricsReport(Collection<FileMeasurements> measurements) {
        this.measurements = measurements;
    }

    public Collection<FileMeasurements> getMeasurements() {
        return measurements;
    }
}
