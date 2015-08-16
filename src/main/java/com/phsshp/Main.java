package com.phsshp;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws CheckstyleException {
        int errorCounter = 0;
        final Checker checker = new Checker();

        try {
            final Configuration config = newConfig();

            System.out.println("=== PRINTING METRICS ===");
            printConfig(config);
            System.out.println("=== END ===");

            final ClassLoader moduleClassLoader = Checker.class.getClassLoader();
            checker.setModuleClassLoader(moduleClassLoader);
            checker.configure(config);
            checker.addListener(new PrintEverythingListener());
            File file = Paths.get("src/main/java/com/phsshp/Main.java").toFile();

            errorCounter = checker.process(Arrays.asList(file));

        }
        finally {
            checker.destroy();
        }

        System.out.println(errorCounter);
    }

    private static Configuration newConfig() {
        DefaultConfiguration rootConfig = new DefaultConfiguration("Checker");
        rootConfig.addAttribute("severity", "warning");

        DefaultConfiguration fileLength = new DefaultConfiguration("FileLength");
        fileLength.addAttribute("max", "0");
        rootConfig.addChild(fileLength);

        DefaultConfiguration treeWalker = new DefaultConfiguration("TreeWalker");
        rootConfig.addChild(treeWalker);

        DefaultConfiguration fileContent = new DefaultConfiguration("FileContentsHolder");
        treeWalker.addChild(fileContent);

        DefaultConfiguration fanOut = new DefaultConfiguration("ClassFanOutComplexity");
        fanOut.addAttribute("max", "0");
        treeWalker.addChild(fanOut);

        DefaultConfiguration complexity = new DefaultConfiguration("CyclomaticComplexity");
        complexity.addAttribute("max", "0");
        treeWalker.addChild(complexity);

        return rootConfig;
    }

    private static void printConfig(Configuration config) throws CheckstyleException {
        System.out.println("NAME: " + config.getName());
        for (String attribute : config.getAttributeNames()) {
            System.out.println(attribute + ": " + config.getAttribute(attribute));
        }
        System.out.println("");

        for (Configuration c : config.getChildren()) {
            printConfig(c);
        }
    }
}
