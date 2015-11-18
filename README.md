# java-code-metrics
Generate Java code metrics

[![Build Status](https://travis-ci.org/phss/java-code-metrics.svg?branch=master)](https://travis-ci.org/phss/java-code-metrics)

## Building

To generate the UberJAR under `build/libs`:

    gradle shadowJar


## Running

    java -jar build/libs/java-code-metrics-all.jar <some source directory>
