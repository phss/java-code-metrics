package com.phsshp;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MetricsReporterTest {

    @Test
    public void reportMetricsForJavaFiles() throws Exception {
        // TODO: crappy test for now
        List<File> files = Arrays.asList(
                new File("src/test/resources/test-project/SomeFile.java"),
                new File("src/test/resources/test-project/pkg1/AnotherInPackage1.java"));

        List<Metrics> metrics = new MetricsReporter().report(files);

        assertThat(metrics.size(), is(2));
        assertThat(metrics.get(0).getFile().getPath(), equalTo("src/test/resources/test-project/SomeFile.java"));
        assertThat(metrics.get(0).getValue(), equalTo(1));
        assertThat(metrics.get(1).getFile().getPath(), equalTo("src/test/resources/test-project/pkg1/AnotherInPackage1.java"));
        assertThat(metrics.get(1).getValue(), equalTo(3));
    }

}