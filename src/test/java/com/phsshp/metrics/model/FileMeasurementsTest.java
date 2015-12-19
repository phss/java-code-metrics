package com.phsshp.metrics.model;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileMeasurementsTest {
    private final FileMeasurements fileMeasurements = new FileMeasurements(new File("somefile.java"));

    @Test
    public void getFirstReportedMetric() throws Exception {
        fileMeasurements.add(new Measurement(MetricType.FILE_SIZE, 32));
        fileMeasurements.add(new Measurement(MetricType.FILE_SIZE, 50));

        assertThat(fileMeasurements.getMetricValue(MetricType.FILE_SIZE, Aggregation.FIRST), is(32.0));
        assertThat(fileMeasurements.getMetricValue(MetricType.FILE_SIZE, Aggregation.FIRST), is(32.));
    }

    @Test
    public void firstReportedMetricIsZeroIfNoMeasurementReported() throws Exception {
        assertThat(fileMeasurements.getMetricValue(MetricType.FILE_SIZE, Aggregation.FIRST), is(0.0));
    }

    @Test
    public void getSumOfMeasurements() throws Exception {
        fileMeasurements.add(new Measurement(MetricType.CYCLOMATIC_COMPLEXITY, 10));
        fileMeasurements.add(new Measurement(MetricType.CYCLOMATIC_COMPLEXITY, 2));

        assertThat(fileMeasurements.getMetricValue(MetricType.CYCLOMATIC_COMPLEXITY, Aggregation.SUM), is(12.0));
    }

    @Test
    public void getSumOfZeroIfNoMeasurementReported() throws Exception {
        assertThat(fileMeasurements.getMetricValue(MetricType.CYCLOMATIC_COMPLEXITY, Aggregation.SUM), is(0.0));
    }

    @Test
    public void getAverageOfMeasurements() throws Exception {
        fileMeasurements.add(new Measurement(MetricType.FANOUT_COMPLEXITY, 1));
        fileMeasurements.add(new Measurement(MetricType.FANOUT_COMPLEXITY, 2));
        fileMeasurements.add(new Measurement(MetricType.FANOUT_COMPLEXITY, 20));
        fileMeasurements.add(new Measurement(MetricType.FANOUT_COMPLEXITY, 2));

        assertThat(fileMeasurements.getMetricValue(MetricType.FANOUT_COMPLEXITY, Aggregation.AVERAGE), is(6.25));
    }

    @Test
    public void getAverageOfZeroIfNoMeasurementReported() throws Exception {
        assertThat(fileMeasurements.getMetricValue(MetricType.FANOUT_COMPLEXITY, Aggregation.AVERAGE), is(0.0));
    }

}