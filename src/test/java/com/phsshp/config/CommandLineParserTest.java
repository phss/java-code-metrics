package com.phsshp.config;

import com.phsshp.metrics.model.Aggregation;
import com.phsshp.metrics.model.MetricType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasEntry;

public class CommandLineParserTest {

    @Test
    public void parseFileNameFromCommandLine() throws Exception {
        CommandLineOptions options = parse("filepath");

        assertThat(options.getSourcePath(), equalTo("filepath"));
    }

    @Test
    public void byDefaultIncludeAllMetrics() throws Exception {
        CommandLineOptions options = parse("filepath");

        assertThat(options.getIncludedMetrics(), containsInAnyOrder(
                MetricType.CYCLOMATIC_COMPLEXITY, MetricType.FANOUT_COMPLEXITY, MetricType.FILE_SIZE));
    }

    @Test
    public void onlyIncludeSpecifiedMetrics() throws Exception {
        CommandLineOptions options = parse("-include", "file_size,fanout_complexity", "filepath");

        assertThat(options.getIncludedMetrics(), containsInAnyOrder(
                MetricType.FANOUT_COMPLEXITY, MetricType.FILE_SIZE));
    }

    @Test
    public void defaultAggregationsPerMetric() throws Exception {
        CommandLineOptions options = parse("filepath");

        assertThat(options.getAggregationPerMetric(), allOf(
                hasEntry(MetricType.FILE_SIZE, Aggregation.FIRST),
                hasEntry(MetricType.CYCLOMATIC_COMPLEXITY, Aggregation.SUM),
                hasEntry(MetricType.FANOUT_COMPLEXITY, Aggregation.FIRST)));
    }

    @Test
    public void overrideAggregationPerMetric() throws Exception {
        CommandLineOptions options = parse("-aggregate", "file_size=sum", "-aggregate", "cyclomatic_complexity=first",
                "-aggregate", "fanout_complexity=average", "filepath");

        assertThat(options.getAggregationPerMetric(), allOf(
                hasEntry(MetricType.FILE_SIZE, Aggregation.SUM),
                hasEntry(MetricType.CYCLOMATIC_COMPLEXITY, Aggregation.FIRST),
                hasEntry(MetricType.FANOUT_COMPLEXITY, Aggregation.AVERAGE)));
    }

    private CommandLineOptions parse(String ...arguments) {
        return new CommandLineParser().parse(arguments);
    }
}