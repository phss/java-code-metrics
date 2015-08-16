package com.phsshp;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    public static void main(String args[]) throws CheckstyleException {
        int errorCounter = 0;
        final Checker checker = new Checker();

        try {
            final Configuration config = ConfigurationLoader.loadConfiguration(
                    "checkstyle-metrics.xml", new PropertiesExpander(new Properties()));

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
}
