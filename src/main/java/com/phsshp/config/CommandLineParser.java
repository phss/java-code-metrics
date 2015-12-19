package com.phsshp.config;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class CommandLineParser {

    private final Options options;

    public CommandLineParser() {
        options = new Options();
        options.addOption(Option.builder("output")
                .argName("file")
                .hasArg()
                .desc("save output to <file>, otherwise print to stdout")
                .build());
        options.addOption(Option.builder("include")
                .argName("metrics")
                .hasArg()
                .desc("comma separated list of metrics to include in the report (by default all metrics are included). " +
                        "Valid metrics are: size, cyclomatic_complexity, fanout_complexity")
                .build());
    }

    public CommandLineOptions parse(String args[]) {
        DefaultParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.getArgList().size() == 0) {
                printHelpAndExit();
            }
            return new CommandLineOptions(commandLine.getArgList().get(0), outputFrom(commandLine));
        } catch (Exception e) {
            System.out.println("Could not parse: " + e.getMessage());
            printHelpAndExit();
            return null;
        }
    }

    private PrintStream outputFrom(CommandLine commandLine) throws FileNotFoundException {
        if (commandLine.hasOption("output")) {
            return new PrintStream(new File(commandLine.getOptionValue("output")));
        } else {
            return System.out;
        }
    }

    private void printHelpAndExit() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java-code-metrics  [OPTION]... [FILE]",
                "Code metrics for Java source code", options, "");
        System.exit(1);
    }
}
