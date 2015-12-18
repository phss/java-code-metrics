package com.phsshp.metrics.reporter.checkstyle;

import com.google.common.collect.ImmutableMap;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CheckstyleConfigurationFactory {

    public static Configuration defaultConfiguration() {
        DefaultConfiguration treeWalker = createModule("TreeWalker", new HashMap<>());
        treeWalker.addChild(createModule("CyclomaticComplexity", ImmutableMap.of("max", "0")));
//        treeWalker.addChild(createModule("ClassFanOutComplexity", ImmutableMap.of("max", "0")));

        DefaultConfiguration rootConfig = createModule("Checker", ImmutableMap.of("severity", "warning"));
        rootConfig.addChild(createModule("FileLength", ImmutableMap.of("max", "0")));
        rootConfig.addChild(treeWalker);

        return rootConfig;
    }

    private static DefaultConfiguration createModule(String moduleName, Map<String, String> attributes) {
        DefaultConfiguration module = new DefaultConfiguration(moduleName);
        attributes.forEach(module::addAttribute);
        return module;
    }
}
