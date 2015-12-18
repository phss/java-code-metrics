package com.phsshp.testutils.matchers;

import com.phsshp.metrics.model.FileMeasurements;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.function.Function;

import static java.lang.String.format;

public class IndividualMeasurementsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private enum MetricToValue {
        FILE_SIZE(FileMeasurements::getFileSize),
        CYCLOMATIC_COMPLEXITY(FileMeasurements::getCyclomaticComplexity);

        private final Function<FileMeasurements, Integer> valueFunction;

        MetricToValue(Function<FileMeasurements, Integer> valueFunction) {
            this.valueFunction = valueFunction;
        }

        public int getValueFrom(FileMeasurements fileMeasurements) {
            return valueFunction.apply(fileMeasurements);
        }
    }

    private final MetricToValue metric;
    private final int expectedValue;

    private IndividualMeasurementsMatcher(MetricToValue metric, int expectedValue) {
        this.metric = metric;
        this.expectedValue = expectedValue;
    }

    public static IndividualMeasurementsMatcher hasFileSize(int expectedValue) {
        return new IndividualMeasurementsMatcher(MetricToValue.FILE_SIZE, expectedValue);
    }

    public static IndividualMeasurementsMatcher hasCyclomaticComplexity(int expectedValue) {
        return new IndividualMeasurementsMatcher(MetricToValue.CYCLOMATIC_COMPLEXITY, expectedValue);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        int actualValue = metric.getValueFrom(actualFileMeasurements);
        mismatchDescription.appendText(format("actual %s with value '%d'", metric.name(), actualValue));
        return actualValue == expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("expected %s with value '%d'", metric.name(), expectedValue));
    }
}
