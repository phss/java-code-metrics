package com.phsshp.metrics.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.Metrics;
import com.phsshp.metrics.MetricsReporter;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class CheckstyleMetricsReporter implements MetricsReporter {

    @Override
    public List<Metrics> report(List<File> files) {
        FileCache fileCache = new FileCache(files);
        List<Metrics> metrics = new ArrayList<>();

        Checker checker = null;
        try {
            checker = new Checker();

            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(CheckstyleConfigurationFactory.defaultConfiguration());
            checker.addListener(new AuditListener() {
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
                    int value = Integer.parseInt(evt.getMessage().split(" ")[3]);
                    try {
                        File metricFile = fileCache.getByAbsolutePath(evt.getFileName());
                        metrics.add(new Metrics(metricFile, value));
                    } catch (NoSuchFileException e) {
                        // TODO: do something
                    }
                }

                @Override
                public void addException(AuditEvent evt, Throwable throwable) {

                }
            });

            checker.process(files);
        } catch (CheckstyleException e) {
            // TODO: rethrow?
        } finally {
            if (checker != null) {
                checker.destroy();
            }
        }
        return metrics;
    }

}
