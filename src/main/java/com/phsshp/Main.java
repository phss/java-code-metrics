package com.phsshp;

import com.phsshp.config.CommandLineOptions;
import com.phsshp.config.CommandLineParser;
import com.phsshp.file.JavaFileLister;
import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReport;
import com.phsshp.metrics.reporter.checkstyle.CheckstyleMetricsReporter;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String args[]) throws Exception {
        CommandLineOptions options = new CommandLineParser().parse(args);
        PrintStream output = options.getOutput();
        Collection<MetricType> metrics = options.getIncludedMetrics();

        List<File> files = new JavaFileLister().list(options.getSourcePath());
        MetricsReport report = new CheckstyleMetricsReporter().report(files);
        List<FileMeasurements> sortedMeasurements = report.getMeasurements()
                .stream().sorted((f1, f2) -> f1.getFile().compareTo(f2.getFile()))
                .collect(Collectors.toList());

        printHeader(output, metrics);
        printMetrics(output, metrics, sortedMeasurements);
    }

    private static void printHeader(PrintStream output, Collection<MetricType> metrics) {
        String metricsString = metrics.stream().map(MetricType::name).map(String::toLowerCase).collect(Collectors.joining(","));
        output.println("file," + metricsString);
    }

    private static void printMetrics(PrintStream output, Collection<MetricType> metrics, List<FileMeasurements> sortedMeasurements) {
        for (FileMeasurements measurements : sortedMeasurements) {
            List<String> metricValues = new ArrayList<>();
            metricValues.add(measurements.getFile().getPath());
            for (MetricType metric : metrics) {
                if (metric == MetricType.FILE_SIZE) {
                    metricValues.add(Integer.toString(measurements.getFileSize()));
                }
                if (metric == MetricType.CYCLOMATIC_COMPLEXITY) {
                    metricValues.add(Integer.toString(measurements.getCyclomaticComplexity()));
                }
                if (metric == MetricType.FANOUT_COMPLEXITY) {
                    metricValues.add(Integer.toString(measurements.getFanoutComplexity()));
                }
            }
            output.println(metricValues.stream().collect(Collectors.joining(",")));
        }
    }
}
