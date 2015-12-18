package com.phsshp.testutils.matchers;

import com.phsshp.metrics.model.FileMeasurements;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static java.lang.String.format;

public class FileMeasurementsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private final String expectedFile;
    private final Matcher<FileMeasurements> fileSizeMatcher;

    private FileMeasurementsMatcher(String expectedFile, Matcher<FileMeasurements> fileSizeMatcher) {
        this.expectedFile = expectedFile;
        this.fileSizeMatcher = fileSizeMatcher;
    }

    public static FileMeasurementsMatcher measurementsMatching(String expectedFile, Matcher<FileMeasurements> fileSizeMatcher) {
        return new FileMeasurementsMatcher(expectedFile, fileSizeMatcher);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        String actualFile = actualFileMeasurements.getFile().getPath();
        mismatchDescription.appendText(format("metric for file '%s'", actualFile));
        fileSizeMatcher.describeMismatch(actualFileMeasurements, mismatchDescription);

        return actualFile.equals(expectedFile) && fileSizeMatcher.matches(actualFileMeasurements);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("metric for file '%s'", expectedFile));
        fileSizeMatcher.describeTo(description);
    }
}
