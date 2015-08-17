package com.phsshp;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
    public void printTargetFileOnConsole() throws CheckstyleException {
        Main.main(new String[] {"src/test/resources/test-project/SomeFile.java"});

        assertThat(outContent.toString(), equalTo(linesString(
                "file",
                "src/test/resources/test-project/SomeFile.java")));
    }

    @Ignore
    @Test
    public void printJavaFilesInTargetDirectoyToConsole() throws CheckstyleException {
        Main.main(new String[] {"src/test/resources/test-project"});

        assertThat(outContent.toString(), equalTo(linesString(
                "file",
                "src/test/resources/test-project/SomeFile.java",
                "src/test/resources/test-project/pkg1/AnotherInPackage1.java",
                "src/test/resources/test-project/pkg1/InPackage1.java",
                "src/test/resources/test-project/pkg2/InPackage2.java")));
    }

    private String linesString(String ...lines) {
        return StringUtils.join(lines, "\n") + "\n";
    }
}
