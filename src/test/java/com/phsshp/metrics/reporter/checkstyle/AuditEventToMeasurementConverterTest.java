package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.checks.metrics.CyclomaticComplexityCheck;
import com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AuditEventToMeasurementConverterTest {

    private AuditEventToMeasurementConverter converter = new AuditEventToMeasurementConverter();

    @Test
    public void convertsFileSizeEvent() throws Exception {
        Measurement measurement = converter.convert(auditEventWith("First second third 42 rest", FileLengthCheck.class));

        assertThat(measurement.getType(), is(MetricType.FILE_SIZE));
        assertThat(measurement.getValue(), is(42));
    }

    @Test
    public void convertsCyclomaticComplexityEvent() throws Exception {
        Measurement measurement = converter.convert(auditEventWith("First second third 30 rest", CyclomaticComplexityCheck.class));

        assertThat(measurement.getType(), is(MetricType.CYCLOMATIC_COMPLEXITY));
        assertThat(measurement.getValue(), is(30));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionForUnsupportedEvent() throws Exception {
        converter.convert(auditEventWith("First second third 30 rest", AuditEventToMeasurementConverterTest.class));
    }

    private AuditEvent auditEventWith(String message, Class<?> eventClass) {
        LocalizedMessage eventMessage = new LocalizedMessage(0, 0, null, null, null, SeverityLevel.WARNING, null, eventClass, message);
        return new AuditEvent(this, "somefile", eventMessage);
    }
}