package com.phsshp.metrics.model;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FileMeasurementsTest {
    private final FileMeasurements fileMeasurements = new FileMeasurements(new File("somefile.java"));

    @Test
    public void fileSizeIsFirstReported() throws Exception {
        fileMeasurements.add(new Measurement(MetricType.FILE_SIZE, 32));
        fileMeasurements.add(new Measurement(MetricType.FILE_SIZE, 50));

        assertThat(fileMeasurements.getFileSize(), is(32));
    }

    @Test
    public void fileSizeIsZeroIfNoMeasurementReported() throws Exception {
        assertThat(fileMeasurements.getFileSize(), is(0));
    }

    @Test
    public void complexityIsSumOfMeasurements() throws Exception {
        fileMeasurements.add(new Measurement(MetricType.CYCLOMATIC_COMPLEXITY, 10));
        fileMeasurements.add(new Measurement(MetricType.CYCLOMATIC_COMPLEXITY, 2));

        assertThat(fileMeasurements.getCyclomaticComplexity(), is(12));
    }

    @Test
    public void complexityIsZeroIfNoMeasurementReported() throws Exception {
        assertThat(fileMeasurements.getCyclomaticComplexity(), is(0));
    }

    @Test
    public void fanoutIsFirstReported() throws Exception {
        fileMeasurements.add(new Measurement(MetricType.FANOUT_COMPLEXITY, 3));
        fileMeasurements.add(new Measurement(MetricType.FANOUT_COMPLEXITY, 4));

        assertThat(fileMeasurements.getFanoutComplexity(), is(3));
    }

    @Test
    public void fanoutIsZeroIfNoMeasurementReported() throws Exception {
        assertThat(fileMeasurements.getFanoutComplexity(), is(0));
    }
}