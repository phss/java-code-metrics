# java-code-metrics
Generate Java code metrics

[![Build Status](https://travis-ci.org/phss/java-code-metrics.svg?branch=master)](https://travis-ci.org/phss/java-code-metrics)

## Building

To generate the UberJAR under `build/libs`:

    gradle shadowJar


## Running

    java -jar build/libs/java-code-metrics-all.jar <some source directory>
    
### Options

    usage: java-code-metrics  [OPTION]... [FILE]
    Code metrics for Java source code
     -aggregate <metric=aggregation>   aggregate a <metric> using the
                                       <aggregation>. Valid aggregations:
                                       first, sum, average
     -include <metrics>                comma separated list of metrics to
                                       include in the report (by default all
                                       metrics are included). Valid metrics
                                       are: file_size, cyclomatic_complexity,
                                       fanout_complexity
     -output <file>                    save output to <file>, otherwise print
                                       to stdout
                                       
### Examples

Report everything using default aggregations (file_size=first, cyclomatic_complexity=sum, fanout_complexity=first):

    java -jar build/libs/java-code-metrics-all.jar src/main/
    ;Output
    file,file_size,cyclomatic_complexity,fanout_complexity
    src/main/java/com/phsshp/Main.java,52.0,4.0,9.0
    src/main/java/com/phsshp/config/CommandLineOptions.java,41.0,5.0,2.0
    src/main/java/com/phsshp/config/CommandLineParser.java,100.0,10.0,10.0
    ...
    
Report cyclomatic complexity as average instead of sum:

    java -jar build/libs/java-code-metrics-all.jar src/main/ -aggregate cyclomatic_complexity=average
    ;Output
    file,file_size,cyclomatic_complexity,fanout_complexity
    src/main/java/com/phsshp/Main.java,52.0,1.3333333333333333,9.0
    src/main/java/com/phsshp/config/CommandLineOptions.java,41.0,1.0,2.0
    src/main/java/com/phsshp/config/CommandLineParser.java,100.0,1.6666666666666667,10.0
    ...
    
Report only file size:

    java -jar build/libs/java-code-metrics-all.jar src/main/ -include file_size
    file,file_size
    ;Output
    src/main/java/com/phsshp/Main.java,52.0
    src/main/java/com/phsshp/config/CommandLineOptions.java,41.0
    src/main/java/com/phsshp/config/CommandLineParser.java,100.0
    ...
