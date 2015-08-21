package com.phsshp.metrics;

import java.io.File;

public class Metrics {

    private final File file;
    private final int fileSize;

    public Metrics(File file, int fileSize) {
        this.file = file;
        this.fileSize = fileSize;
    }

    public File getFile() {
        return file;
    }

    public int getFileSize() {
        return fileSize;
    }
}
