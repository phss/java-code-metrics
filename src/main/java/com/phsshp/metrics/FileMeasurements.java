package com.phsshp.metrics;

import java.io.File;

public class FileMeasurements {

    private final File file;
    private final Measurement fileSizeMeasurement;

    public FileMeasurements(File file, Measurement fileSizeMeasurement) {
        this.file = file;
        this.fileSizeMeasurement = fileSizeMeasurement;
    }

    public File getFile() {
        return file;
    }

    public int getFileSize() {
        return fileSizeMeasurement.getValue();
    }
}
