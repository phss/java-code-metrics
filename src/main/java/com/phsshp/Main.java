package com.phsshp;

import com.phsshp.config.CommandLineOptions;
import com.phsshp.config.CommandLineParser;
import com.phsshp.file.JavaFileLister;
import com.phsshp.metrics.model.Aggregation;
import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReport;
import com.phsshp.metrics.reporter.checkstyle.CheckstyleMetricsReporter;

import java.io.File;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {

    public static void main(String args[]) throws Exception {
        CommandLineOptions options = new CommandLineParser().parse(args);
        PrintStream output = options.getOutput();
        Collection<MetricType> metrics = options.getIncludedMetrics();
        Map<MetricType, Aggregation> aggregationPerMetric = options.getAggregationPerMetric();

        List<File> files = new JavaFileLister().list(options.getSourcePath());
        MetricsReport report = new CheckstyleMetricsReporter().report(files);
        List<FileMeasurements> sortedMeasurements = report.getMeasurements()
                .stream().sorted((f1, f2) -> f1.getFile().compareTo(f2.getFile()))
                .collect(Collectors.toList());

        printHeader(output, metrics);
        printMetrics(output, metrics, aggregationPerMetric, sortedMeasurements);
    }

    private static void printHeader(PrintStream output, Collection<MetricType> metrics) {
        String metricsString = metrics.stream().map(MetricType::name).map(String::toLowerCase).collect(Collectors.joining(","));
        output.println("file," + metricsString);
    }

    private static void printMetrics(PrintStream output, Collection<MetricType> metrics, Map<MetricType, Aggregation> aggregationPerMetric, List<FileMeasurements> sortedMeasurements) {
        for (FileMeasurements measurements : sortedMeasurements) {
            String metricValuesString = metrics.stream()
                    .map(metric -> measurements.getMetricValue(metric, aggregationPerMetric.get(metric)))
                    .map(m -> Double.toString(m))
                    .collect(Collectors.joining(","));
            output.println(measurements.getFile().getPath() + "," + metricValuesString);
        }
    }
}
