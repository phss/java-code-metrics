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

    public int getFileSize() {
        return getFirstValue(MetricType.FILE_SIZE);
    }

    public int getCyclomaticComplexity() {
        return getSumOfValues(MetricType.CYCLOMATIC_COMPLEXITY);
    }

    private int getFirstValue(MetricType metric) {
        return measurementsFor(metric).findFirst().map(Measurement::getValue).orElse(0);
    }

    private int getSumOfValues(MetricType metric) {
        return measurementsFor(metric).mapToInt(Measurement::getValue).sum();
    }

    private Stream<Measurement> measurementsFor(MetricType metric) {
        return measurements.stream().filter(m -> m.getType() ==  metric);
    }
}
