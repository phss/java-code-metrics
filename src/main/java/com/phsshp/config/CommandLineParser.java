package com.phsshp.config;

import com.phsshp.metrics.model.Aggregation;
import com.phsshp.metrics.model.MetricType;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLineParser {

    private final Options options;

    public CommandLineParser() {
        String validMetrics = Stream.of(MetricType.values())
                .map(MetricType::name)
                .map(String::toLowerCase)
                .collect(Collectors.joining(", "));

        options = new Options();
        options.addOption(Option.builder("output")
                .argName("file")
                .hasArg()
                .desc("save output to <file>, otherwise print to stdout")
                .build());
        options.addOption(Option.builder("include")
                .argName("metrics")
                .hasArg()
                .desc("comma separated list of metrics to include in the report (by default all metrics are included). " +
                        "Valid metrics are: " + validMetrics)
                .build());
        options.addOption(Option.builder("aggregate")
                .argName("metric=aggregation")
                .numberOfArgs(2)
                .valueSeparator()
                .desc("aggregate a <metric> using the <aggregation>. " +
                        "Valid aggregations: first, sum, average")
                .build());
    }

    public CommandLineOptions parse(String args[]) {
        DefaultParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.getArgList().size() == 0) {
                printHelpAndExit();
            }
            return new CommandLineOptions(commandLine.getArgList().get(0),
                    outputFrom(commandLine),
                    metricsFrom(commandLine),
                    aggregationsFrom(commandLine));
        } catch (Exception e) {
            System.out.println("Could not parse: " + e.getMessage());
            printHelpAndExit();
            return null;
        }
    }

    private PrintStream outputFrom(CommandLine commandLine) throws FileNotFoundException {
        if (commandLine.hasOption("output")) {
            return new PrintStream(new File(commandLine.getOptionValue("output")));
        } else {
            return System.out;
        }
    }

    private Collection<MetricType> metricsFrom(CommandLine commandLine) {
        if (!commandLine.hasOption("include")) {
            return Arrays.asList(MetricType.values());
        }
        return Stream.of(commandLine.getOptionValue("include").split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(MetricType::valueOf)
                .collect(Collectors.toList());
    }

    private Map<MetricType, Aggregation> aggregationsFrom(CommandLine commandLine) {
        Map<MetricType, Aggregation> metricTypeAggregationMap = MetricType.defaultAggregationsPerMetric();
        commandLine.getOptionProperties("aggregate").forEach((metricName, aggregateName) -> {
            metricTypeAggregationMap.put(MetricType.valueOf(((String)metricName).toUpperCase()),
                                         Aggregation.valueOf(((String)aggregateName).toUpperCase()));
        });
        return metricTypeAggregationMap;
    }

    private void printHelpAndExit() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java-code-metrics  [OPTION]... [FILE]",
                "Code metrics for Java source code", options, "");
        System.exit(1);
    }
}
