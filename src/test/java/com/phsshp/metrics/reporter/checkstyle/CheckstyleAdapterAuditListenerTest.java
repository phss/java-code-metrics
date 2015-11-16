package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.model.MetricsReportBuilder;
import com.phsshp.metrics.reporter.ReportingException;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static com.phsshp.testutils.matchers.FileMeasurementsMatcher.measurementsMatching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CheckstyleAdapterAuditListenerTest {

    private final File testFile = new File("src/test/resources/test-project/SomeFile.java");
    private final FileCache fileCache = new FileCache(Arrays.asList(testFile));
    private final MetricsReportBuilder metricsReportBuilder = new MetricsReportBuilder();
    private final CheckstyleAdapterAuditListener auditListener = new CheckstyleAdapterAuditListener(fileCache, metricsReportBuilder);

    @Test
    public void hardcodedMetricCreation() throws Exception {
        auditListener.addError(new AuditEvent(this, testFile.getAbsolutePath(), checkstyleMessageWith("First second third 42 rest", FileLengthCheck.class)));

        assertThat(metricsReportBuilder.build().getMeasurements(), contains(
                measurementsMatching("src/test/resources/test-project/SomeFile.java", 42)));
    }

    @Test(expected = ReportingException.class)
    public void throwExceptionWhenReportingErrorForFileNotTracked() throws Exception {
        auditListener.addError(new AuditEvent(this, "NoSuchFile.java",
            checkstyleMessageWith("First second third 42 rest", FileLengthCheck.class)));
    }

    @Test(expected = ReportingException.class)
    public void throwExceptionWhenReportingErrorForUnsupportedMetric() throws Exception {
        auditListener.addError(new AuditEvent(this, testFile.getAbsolutePath(),
            checkstyleMessageWith("First second third 42 rest", CheckstyleAdapterAuditListenerTest.class)));
    }

    private LocalizedMessage checkstyleMessageWith(String message, Class<?> eventClass) {
        return new LocalizedMessage(0, 0, null, null, null, SeverityLevel.WARNING, null, eventClass, message);
    }
}