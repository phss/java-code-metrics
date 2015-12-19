package com.phsshp.metrics.model;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileMeasurements {

    private final File file;
    private final ArrayList<Measurement> measurements;

    public FileMeasurements(File file) {
        this.file = file;
        measurements = new ArrayList<>();
    }

    public void add(Measurement measurement) {
        this.measurements.add(measurement);
    }

    public File getFile() {
        return file;
    }

    public double getMetricValue(MetricType metric, Aggregation aggregation) {
        Stream<Measurement> measurements = measurementsFor(metric);
        return aggregation.aggregateToInt(measurements);
    }

    private Stream<Measurement> measurementsFor(MetricType metric) {
        return measurements.stream().filter(m -> m.getType() ==  metric);
    }
}
