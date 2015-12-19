package com.phsshp;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void printTargetFileOnConsole() throws Exception {
        Main.main(new String[] {"src/test/resources/test-project/SomeFile.java"});

        assertThat(outContent.toString(), equalTo(linesString(
                "file,file_size,cyclomatic_complexity,fanout_complexity",
                "src/test/resources/test-project/SomeFile.java,40,11,0")));
    }

    @Test
    public void printJavaFilesInTargetDirectoyToConsole() throws Exception {
        Main.main(new String[] {"src/test/resources/test-project"});

        assertThat(outContent.toString(), equalTo(linesString(
                "file,file_size,cyclomatic_complexity,fanout_complexity",
                "src/test/resources/test-project/SomeFile.java,40,11,0",
                "src/test/resources/test-project/pkg1/AnotherInPackage1.java,4,0,0",
                "src/test/resources/test-project/pkg1/InPackage1.java,8,1,1",
                "src/test/resources/test-project/pkg2/InPackage2.java,10,2,0")));
    }

    @Test
    public void printIncludedMetrics() throws Exception {
        Main.main(new String[] {"-include", "cyclomatic_complexity,file_size", "src/test/resources/test-project"});

        assertThat(outContent.toString(), equalTo(linesString(
                "file,cyclomatic_complexity,file_size",
                "src/test/resources/test-project/SomeFile.java,11,40",
                "src/test/resources/test-project/pkg1/AnotherInPackage1.java,0,4",
                "src/test/resources/test-project/pkg1/InPackage1.java,1,8",
                "src/test/resources/test-project/pkg2/InPackage2.java,2,10")));
    }

    private String linesString(String ...lines) {
        return StringUtils.join(lines, "\n") + "\n";
    }
}
