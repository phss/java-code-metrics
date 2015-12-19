package com.phsshp.config;

import com.phsshp.metrics.model.Aggregation;
import com.phsshp.metrics.model.MetricType;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;

public class CommandLineOptions {

    private final String sourcePath;
    private final PrintStream output;
    private final Collection<MetricType> includedMetrics;
    private final Map<MetricType, Aggregation> aggregationPerMetric;

    public CommandLineOptions(String sourcePath, PrintStream output,
                              Collection<MetricType> includedMetrics, Map<MetricType, Aggregation> aggregationPerMetric)
    {
        this.sourcePath = sourcePath;
        this.output = output;
        this.includedMetrics = includedMetrics;
        this.aggregationPerMetric = aggregationPerMetric;
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

    public Map<MetricType, Aggregation> getAggregationPerMetric() {
        return aggregationPerMetric;
    }
}
