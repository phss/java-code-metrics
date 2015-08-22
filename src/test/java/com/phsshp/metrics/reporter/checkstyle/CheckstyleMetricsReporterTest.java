package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.metrics.model.MetricsReport;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.phsshp.testutils.matchers.FileMeasurementsMatcher.measurementsMatching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CheckstyleMetricsReporterTest {

    @Test
    public void reportMetricsForJavaFiles() throws Exception {
        // TODO: at some point make test less end to end
        List<File> files = Arrays.asList(
                new File("src/test/resources/test-project/SomeFile.java"),
                new File("src/test/resources/test-project/pkg1/AnotherInPackage1.java"));

        MetricsReport report = new CheckstyleMetricsReporter().report(files);

        assertThat(report.getMeasurements(), contains(
                measurementsMatching("src/test/resources/test-project/SomeFile.java", 40),
                measurementsMatching("src/test/resources/test-project/pkg1/AnotherInPackage1.java", 3)));
    }

}