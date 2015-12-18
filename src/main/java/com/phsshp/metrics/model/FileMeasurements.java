package com.phsshp.metrics.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

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
        return getFirstValue(MetricType.CYCLOMATIC_COMPLEXITY);
    }

    private int getFirstValue(MetricType metric) {
        Optional<Measurement> measurement = measurements.stream().filter(m -> m.getType() ==  metric).findFirst();
        return measurement.map(Measurement::getValue).orElse(0);
    }
}
