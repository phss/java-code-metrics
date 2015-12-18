package com.phsshp.config;

import org.apache.commons.cli.*;

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
            if (commandLine.getArgList().size() == 0) {
                printHelpAndExit();
            }
            return new CommandLineOptions(commandLine.getArgList().get(0));
        } catch (ParseException e) {
            System.out.println("Could not parse: " + e.getMessage());
            printHelpAndExit();
            return null;
        }
    }

    private void printHelpAndExit() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("java-code-metrics  [OPTION]... [FILE]",
                "Code metrics for Java source code", options, "");
        System.exit(1);
    }
}
