package com.phsshp.metrics.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.Metrics;
import com.phsshp.metrics.MetricsBuilder;
import com.phsshp.metrics.MetricsReporter;
import com.phsshp.metrics.ReportingException;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.File;
import java.util.List;

public class CheckstyleMetricsReporter implements MetricsReporter {

    @Override
    public List<Metrics> report(List<File> files) {
        MetricsBuilder metricsBuilder = new MetricsBuilder();
        runCheckstyles(files,
                CheckstyleConfigurationFactory.defaultConfiguration(),
                new CheckstyleAdapterAuditListener(new FileCache(files), metricsBuilder));
        return metricsBuilder.build();
    }

    private void runCheckstyles(List<File> files, Configuration configuration, AuditListener auditListener) {
        Checker checker = null;
        try {
            checker = new Checker();

            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(configuration);
            checker.addListener(auditListener);

            checker.process(files);
        } catch (CheckstyleException e) {
            throw new ReportingException("Checkstyle failed", e);
        } finally {
            if (checker != null) {
                checker.destroy();
            }
        }
    }

}
