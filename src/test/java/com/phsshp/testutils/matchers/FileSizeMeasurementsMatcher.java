package com.phsshp.testutils.matchers;

import com.phsshp.metrics.model.FileMeasurements;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static java.lang.String.format;

public class FileSizeMeasurementsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private final int expectedValue;

    private FileSizeMeasurementsMatcher(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    public static FileSizeMeasurementsMatcher hasFileSize(int expectedValue) {
        return new FileSizeMeasurementsMatcher(expectedValue);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        int actualValue = actualFileMeasurements.getFileSize();
        mismatchDescription.appendText(format("file size with value '%d'", actualValue));
        return actualValue == expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("file size with value '%d'", expectedValue));
    }
}
