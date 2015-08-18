package com.phsshp;

import java.io.File;

public class Metrics {

    private final File file;
    private final int value;

    public Metrics(File file, int value) {
        this.file = file;
        this.value = value;
    }

    public File getFile() {
        return file;
    }

    public int getValue() {
        return value;
    }
}
