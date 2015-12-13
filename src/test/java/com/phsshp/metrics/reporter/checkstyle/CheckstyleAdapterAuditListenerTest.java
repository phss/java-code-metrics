package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReportBuilder;
import com.phsshp.metrics.reporter.ReportingException;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static com.phsshp.testutils.matchers.FileMeasurementsMatcher.measurementsMatching;
import static com.phsshp.testutils.matchers.FileSizeMeasurementsMatcher.hasFileSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckstyleAdapterAuditListenerTest {

    private final File testFile = new File("src/test/resources/test-project/SomeFile.java");
    private final AuditEventToMeasurementConverter auditEventConverter = mock(AuditEventToMeasurementConverter.class);
    private final FileCache fileCache = new FileCache(Arrays.asList(testFile));
    private final MetricsReportBuilder metricsReportBuilder = new MetricsReportBuilder();
    private final CheckstyleAdapterAuditListener auditListener = new CheckstyleAdapterAuditListener(auditEventConverter,
            fileCache, metricsReportBuilder);

    @Test
    public void hardcodedMetricCreation() throws Exception {
        AuditEvent event = new AuditEvent(this, testFile.getAbsolutePath(), null);
        when(auditEventConverter.convert(event)).thenReturn(new Measurement(MetricType.FILE_SIZE, 42));

        auditListener.addError(event);

        assertThat(metricsReportBuilder.build().getMeasurements(), contains(
                measurementsMatching("src/test/resources/test-project/SomeFile.java", hasFileSize(42))));
    }

    @Test(expected = ReportingException.class)
    public void throwExceptionWhenReportingErrorForFileNotTracked() throws Exception {
        auditListener.addError(new AuditEvent(this, "NoSuchFile.java", null));
    }

    @Test(expected = ReportingException.class)
    public void throwExceptionWhenReportingErrorForUnsupportedMetric() throws Exception {
        AuditEvent event = new AuditEvent(this, testFile.getAbsolutePath(), null);
        when(auditEventConverter.convert(event)).thenThrow(new IllegalArgumentException());

        auditListener.addError(event);
    }

}