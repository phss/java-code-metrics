package com.phsshp.config;

import com.phsshp.metrics.model.MetricType;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

public class CommandLineOptions {

    private final String sourcePath;
    private final PrintStream output;
    private final Collection<MetricType> includedMetrics;

    public CommandLineOptions(String sourcePath, PrintStream output, Collection<MetricType> includedMetrics) {
        this.sourcePath = sourcePath;
        this.output = output;
        this.includedMetrics = includedMetrics;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public PrintStream getOutput() {
        return output;
    }

    public Collection<MetricType> getIncludedMetrics() {
        return includedMetrics;
    }
}
