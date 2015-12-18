package com.phsshp.config;

public class CommandLineOptions {

    private final String sourcePath;

    public CommandLineOptions(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourcePath() {
        return sourcePath;
    }
}
