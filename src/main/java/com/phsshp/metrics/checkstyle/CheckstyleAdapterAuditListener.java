package com.phsshp.metrics.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.Metrics;
import com.phsshp.metrics.MetricsBuilder;
import com.phsshp.metrics.ReportingException;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;

import java.io.File;
import java.nio.file.NoSuchFileException;

class CheckstyleAdapterAuditListener implements AuditListener {
    private final FileCache fileCache;
    private final MetricsBuilder metrics;

    public CheckstyleAdapterAuditListener(FileCache fileCache, MetricsBuilder metrics) {
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
        // TODO: improve parsing and file/metric wrangling
        int value = Integer.parseInt(evt.getMessage().split(" ")[3]);
        try {
            File metricFile = fileCache.getByAbsolutePath(evt.getFileName());
            metrics.add(new Metrics(metricFile, value));
        } catch (NoSuchFileException e) {
            throw new ReportingException("No such file", e);
        }
    }

    @Override
    public void addException(AuditEvent evt, Throwable throwable) {

    }
}
