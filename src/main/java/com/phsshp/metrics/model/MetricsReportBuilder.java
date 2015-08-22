package com.phsshp.metrics.model;

import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.Measurement;

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
