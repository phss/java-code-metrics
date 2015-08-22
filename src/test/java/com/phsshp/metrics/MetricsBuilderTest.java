package com.phsshp.metrics;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.phsshp.testutils.matchers.MetricsMatcher.metricsMatching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class MetricsBuilderTest {

    @Test
    public void buildMetrics() throws Exception {
        MetricsBuilder builder = new MetricsBuilder();
        builder.add(new File("somefile.java"), 42);
        builder.add(new File("anotherfile.java"), 30);

        List<Metrics> metrics = builder.build();

        assertThat(metrics, contains(
                metricsMatching("somefile.java", 42),
                metricsMatching("anotherfile.java", 30)));
    }
}