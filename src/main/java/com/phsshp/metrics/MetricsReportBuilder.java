package com.phsshp.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MetricsReportBuilder {

    private final List<FileMeasurements> measurements;

    public MetricsReportBuilder() {
        measurements = new ArrayList<>();
    }

    public void add(File file, Measurement measurement) {
        measurements.add(new FileMeasurements(file, measurement));
    }

    public List<FileMeasurements> build() {
        return measurements;
    }
}
