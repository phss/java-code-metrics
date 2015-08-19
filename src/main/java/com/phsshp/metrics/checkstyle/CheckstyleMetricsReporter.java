package com.phsshp.metrics.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.Metrics;
import com.phsshp.metrics.MetricsBuilder;
import com.phsshp.metrics.MetricsReporter;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.io.File;
import java.util.List;

public class CheckstyleMetricsReporter implements MetricsReporter {

    @Override
    public List<Metrics> report(List<File> files) {
        FileCache fileCache = new FileCache(files);
        MetricsBuilder metrics = new MetricsBuilder();

        Checker checker = null;
        try {
            checker = new Checker();

            checker.setModuleClassLoader(Checker.class.getClassLoader());
            checker.configure(CheckstyleConfigurationFactory.defaultConfiguration());
            checker.addListener(new CheckstyleAdapterAuditListener(fileCache, metrics));

            checker.process(files);
        } catch (CheckstyleException e) {
            // TODO: rethrow?
        } finally {
            if (checker != null) {
                checker.destroy();
            }
        }
        return metrics.build();
    }

}
