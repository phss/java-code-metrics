package com.phsshp.metrics.reporter.model;

import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReport;
import com.phsshp.metrics.model.MetricsReportBuilder;
import org.junit.Test;

import java.io.File;

import static com.phsshp.testutils.matchers.FileMeasurementsMatcher.measurementsMatching;
import static com.phsshp.testutils.matchers.IndividualMeasurementsMatcher.hasCyclomaticComplexity;
import static com.phsshp.testutils.matchers.IndividualMeasurementsMatcher.hasFileSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;

public class MetricsReportBuilderTest {

    @Test
    public void buildMetrics() throws Exception {
        MetricsReportBuilder builder = new MetricsReportBuilder();
        builder.add(new File("somefile.java"), new Measurement(MetricType.FILE_SIZE, 42));
        builder.add(new File("somefile.java"), new Measurement(MetricType.CYCLOMATIC_COMPLEXITY, 5));
        builder.add(new File("anotherfile.java"), new Measurement(MetricType.FILE_SIZE, 30));

        MetricsReport report = builder.build();

        assertThat(report.getMeasurements(), contains(
                measurementsMatching("somefile.java", allOf(hasFileSize(42.0), hasCyclomaticComplexity(5.0))),
                measurementsMatching("anotherfile.java", allOf(hasFileSize(30.), hasCyclomaticComplexity(0.0)))));
    }
}