package com.phsshp.metrics;

import java.io.File;

public class Metrics {

    private final File file;
    private final Measure fileSizeMeasure;

    public Metrics(File file, Measure fileSizeMeasure) {
        this.file = file;
        this.fileSizeMeasure = fileSizeMeasure;
    }

    public File getFile() {
        return file;
    }

    public int getFileSize() {
        return fileSizeMeasure.getValue();
    }
}
