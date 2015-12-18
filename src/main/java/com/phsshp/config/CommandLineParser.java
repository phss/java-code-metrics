package com.phsshp.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class CommandLineParser {

    private final Options options;

    public CommandLineParser() {
        options = new Options();
        options.addOption("output", true, "Filename to save output (default is stdout)");
    }

    public CommandLineOptions parse(String args[]) {
        DefaultParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
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
