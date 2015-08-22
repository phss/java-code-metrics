package com.phsshp.testutils.matchers;

import com.phsshp.metrics.FileMeasurements;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static java.lang.String.format;

public class MetricsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private final String expectedFile;
    private final int expectedValue;

    private MetricsMatcher(String expectedFile, int expectedValue) {
        this.expectedFile = expectedFile;
        this.expectedValue = expectedValue;
    }

    public static MetricsMatcher metricsMatching(String expectedFile, int expectedValue) {
        return new MetricsMatcher(expectedFile, expectedValue);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        String actualFile = actualFileMeasurements.getFile().getPath();
        int actualValue = actualFileMeasurements.getFileSize();
        mismatchDescription.appendText(format("metric for file '%s' with value '%d'", actualFile, actualValue));

        return actualFile.equals(expectedFile) && actualValue == expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("metric for file '%s' with value '%d'", expectedFile, expectedValue));
    }
}
