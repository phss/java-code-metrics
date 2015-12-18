package com.phsshp.testutils.matchers;

import com.phsshp.metrics.model.FileMeasurements;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static java.lang.String.format;

public class ComplexityMeasurementsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private final int expectedValue;

    private ComplexityMeasurementsMatcher(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    public static ComplexityMeasurementsMatcher hasComplexity(int expectedValue) {
        return new ComplexityMeasurementsMatcher(expectedValue);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        int actualValue = actualFileMeasurements.getCyclomaticComplexity();
        mismatchDescription.appendText(format("actual complexity with value '%d'", actualValue));
        return actualValue == expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("expected complexity with value '%d'", expectedValue));
    }
}
