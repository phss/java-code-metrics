package com.phsshp.config;

import java.io.PrintStream;

public class CommandLineOptions {

    private final String sourcePath;
    private final PrintStream output;

    public CommandLineOptions(String sourcePath, PrintStream output) {
        this.sourcePath = sourcePath;
        this.output = output;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public PrintStream getOutput() {
        return output;
    }
}
