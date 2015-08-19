package com.phsshp.metrics.checkstyle;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.Configuration;

public class CheckstyleConfigurationFactory {

    public static Configuration defaultConfiguration() {
        DefaultConfiguration rootConfig = new DefaultConfiguration("Checker");
        rootConfig.addAttribute("severity", "warning");

        DefaultConfiguration fileLength = new DefaultConfiguration("FileLength");
        fileLength.addAttribute("max", "0");
        rootConfig.addChild(fileLength);

        return rootConfig;
    }
}
