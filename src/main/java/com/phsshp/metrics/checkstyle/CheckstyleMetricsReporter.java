package com.phsshp.metrics.checkstyle;

import com.phsshp.metrics.MetricsReporter;
import com.phsshp.metrics.Metrics;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckstyleMetricsReporter implements MetricsReporter {

    @Override
    public List<Metrics> report(List<File> files) {
        List<Metrics> metrics = new ArrayList<>();

        Checker checker = null;
        try {
            checker = new Checker();

            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(newConfig());
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
                    File errorFile = new File(evt.getFileName());
                    File metricFile = null;
                    for (File file : files) {
                        if (file.getAbsolutePath().equals(errorFile.getAbsolutePath())) {
                            metricFile = file;
                            break;
                        }
                    }
                    metrics.add(new Metrics(metricFile, value));
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

    private Configuration newConfig() {
        DefaultConfiguration rootConfig = new DefaultConfiguration("Checker");
        rootConfig.addAttribute("severity", "warning");

        DefaultConfiguration fileLength = new DefaultConfiguration("FileLength");
        fileLength.addAttribute("max", "0");
        rootConfig.addChild(fileLength);

        return rootConfig;
    }

}
