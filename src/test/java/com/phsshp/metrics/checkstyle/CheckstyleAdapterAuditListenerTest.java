package com.phsshp.metrics.checkstyle;

import com.phsshp.file.FileCache;
import com.phsshp.metrics.MetricsBuilder;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.LocalizedMessage;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static com.phsshp.testutils.matchers.MetricsMatcher.metricsMatching;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class CheckstyleAdapterAuditListenerTest {

    @Test
    public void hardcodedMetricCreation() throws Exception {
        File testFile = new File("src/test/resources/test-project/SomeFile.java");
        FileCache fileCache = new FileCache(Arrays.asList(testFile));
        MetricsBuilder metricsBuilder = new MetricsBuilder();

        CheckstyleAdapterAuditListener auditListener = new CheckstyleAdapterAuditListener(fileCache, metricsBuilder);
        auditListener.addError(new AuditEvent(this, testFile.getAbsolutePath(), checkstyleMessageWith("First second third 42 rest")));

        assertThat(metricsBuilder.build(), contains(
                metricsMatching("src/test/resources/test-project/SomeFile.java", 42)));
    }


    private LocalizedMessage checkstyleMessageWith(String message) {
        return new LocalizedMessage(0, 0, null, null, null, SeverityLevel.WARNING, null, FileLengthCheck.class, message);
    }
}