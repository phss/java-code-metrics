package com.phsshp.metrics.checkstyle;

import com.phsshp.metrics.Metrics;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.phsshp.testutils.matchers.MetricsMatcher.metricsMatching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CheckstyleMetricsReporterTest {

    @Test
    public void reportMetricsForJavaFiles() throws Exception {
        List<File> files = Arrays.asList(
                new File("src/test/resources/test-project/SomeFile.java"),
                new File("src/test/resources/test-project/pkg1/AnotherInPackage1.java"));

        List<Metrics> metrics = new CheckstyleMetricsReporter().report(files);

        assertThat(metrics, contains(
                metricsMatching("src/test/resources/test-project/SomeFile.java", 1),
                metricsMatching("src/test/resources/test-project/pkg1/AnotherInPackage1.java", 3)));
    }

}