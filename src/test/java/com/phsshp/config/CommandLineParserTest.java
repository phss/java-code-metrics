package com.phsshp.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CommandLineParserTest {

    @Test
    public void parseFileNameFromCommandLine() throws Exception {
        CommandLineOptions options = parse("filepath");

        assertThat(options.getSourcePath(), equalTo("filepath"));
    }


    private CommandLineOptions parse(String ...arguments) {
        return new CommandLineParser().parse(arguments);
    }
}