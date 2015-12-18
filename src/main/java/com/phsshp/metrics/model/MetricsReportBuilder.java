package com.phsshp.metrics.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MetricsReportBuilder {

    private final Map<File, FileMeasurements> measurements;

    public MetricsReportBuilder() {
        measurements = new HashMap<>();
    }

    public void add(File file, Measurement measurement) {
        // TODO remove filtering by file size
        if (measurement.getType() == MetricType.FILE_SIZE && !measurements.containsKey(file)) {
            FileMeasurements fileMeasurements = new FileMeasurements(file);
            fileMeasurements.add(measurement);
            measurements.put(file, fileMeasurements);
        }
    }

    public MetricsReport build() {
        return new MetricsReport(measurements.values());
    }
}
