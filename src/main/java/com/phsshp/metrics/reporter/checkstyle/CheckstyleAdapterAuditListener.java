package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReportBuilder;
import com.phsshp.metrics.reporter.ReportingException;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck;

import java.io.File;
import java.nio.file.NoSuchFileException;

import static java.lang.String.format;

class CheckstyleAdapterAuditListener implements AuditListener {
    private final AuditEventToMeasurementConverter auditEventConverter;
    private final FileCache fileCache;
    private final MetricsReportBuilder metrics;

    public CheckstyleAdapterAuditListener(AuditEventToMeasurementConverter auditEventConverter,
                                          FileCache fileCache, MetricsReportBuilder metrics)
    {
        this.auditEventConverter = auditEventConverter;
        this.fileCache = fileCache;
        this.metrics = metrics;
    }

    @Override
    public void auditStarted(AuditEvent evt) {

    }

    @Override
    public void auditFinished(AuditEvent evt) {

    }

    @Override
    public void fileStarted(AuditEvent evt) {

    }

    @Override
    public void fileFinished(AuditEvent evt) {

    }

    @Override
    public void addError(AuditEvent evt) {
        metrics.add(fileFor(evt), auditEventConverter.convert(evt));
    }

    private File fileFor(AuditEvent evt) {
        try {
            return fileCache.getByAbsolutePath(evt.getFileName());
        } catch (NoSuchFileException e) {
            throw new ReportingException("No such file", e);
        }
    }

    @Override
    public void addException(AuditEvent evt, Throwable throwable) {
        System.err.println(format("Exception thrown while processing %s", evt.getFileName()));
        throwable.printStackTrace();
    }
}
