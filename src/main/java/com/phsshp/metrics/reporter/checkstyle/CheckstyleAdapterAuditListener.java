package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.model.MetricsReportBuilder;
import com.phsshp.metrics.reporter.ReportingException;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;

import java.io.File;
import java.nio.file.NoSuchFileException;

class CheckstyleAdapterAuditListener implements AuditListener {
    private final FileCache fileCache;
    private final MetricsReportBuilder metrics;

    public CheckstyleAdapterAuditListener(FileCache fileCache, MetricsReportBuilder metrics) {
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
        metrics.add(fileFor(evt), measureFor(evt));
    }

    private File fileFor(AuditEvent evt) {
        try {
            return fileCache.getByAbsolutePath(evt.getFileName());
        } catch (NoSuchFileException e) {
            throw new ReportingException("No such file", e);
        }
    }

    private Measurement measureFor(AuditEvent evt) {
        int value = Integer.parseInt(evt.getMessage().split(" ")[3]);
        return new Measurement(MetricType.FILE_SIZE, value);
    }

    @Override
    public void addException(AuditEvent evt, Throwable throwable) {
        // TODO: handle exceptions being thrown. Print it?
    }
}
