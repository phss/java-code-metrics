package com.phsshp.testutils.matchers;

import com.phsshp.metrics.model.FileMeasurements;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static com.phsshp.testutils.matchers.FileSizeMeasurementsMatcher.hasFileSize;
import static java.lang.String.format;

public class FileMeasurementsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private final String expectedFile;
    private final int expectedValue;
    private final FileSizeMeasurementsMatcher fileSizeMatcher;

    private FileMeasurementsMatcher(String expectedFile, int expectedValue) {
        this.expectedFile = expectedFile;
        this.expectedValue = expectedValue;
        fileSizeMatcher = hasFileSize(expectedValue);
    }

    public static FileMeasurementsMatcher measurementsMatching(String expectedFile, int expectedValue) {
        return new FileMeasurementsMatcher(expectedFile, expectedValue);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        String actualFile = actualFileMeasurements.getFile().getPath();
        mismatchDescription.appendText(format("metric for file '%s'", actualFile));

        return actualFile.equals(expectedFile) && fileSizeMatcher.matchesSafely(actualFileMeasurements, mismatchDescription);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("metric for file '%s'", expectedFile));
        fileSizeMatcher.describeTo(description);
    }
}
