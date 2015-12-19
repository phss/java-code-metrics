package com.phsshp.config;

import com.phsshp.metrics.model.MetricType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

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

    private CommandLineOptions parse(String ...arguments) {
        return new CommandLineParser().parse(arguments);
    }
}