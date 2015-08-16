package com.phsshp;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws CheckstyleException {
        int errorCounter = 0;
        final Checker checker = new Checker();

        try {

            final ClassLoader moduleClassLoader = Checker.class.getClassLoader();
            checker.setModuleClassLoader(moduleClassLoader);
            //checker.configure(config);
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
