package com.phsshp.metrics.reporter.model;

import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReportBuilder;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.phsshp.testutils.matchers.MetricsMatcher.metricsMatching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class MetricsReportBuilderTest {

    @Test
    public void buildMetrics() throws Exception {
        MetricsReportBuilder builder = new MetricsReportBuilder();
        builder.add(new File("somefile.java"), new Measurement(MetricType.FILE_SIZE, 42));
        builder.add(new File("anotherfile.java"), new Measurement(MetricType.FILE_SIZE, 30));

        List<FileMeasurements> metrics = builder.build();

        assertThat(metrics, contains(
                metricsMatching("somefile.java", 42),
                metricsMatching("anotherfile.java", 30)));
    }
}