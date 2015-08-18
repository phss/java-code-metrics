package com.phsshp;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class FileLister {

    public List<File> list(String path) {
        File file = new File(path);
        if (file.exists()) {
            return Arrays.asList(file);
        }
        throw new ConfigurationException(format("Path '%s' not found", path));
    }
}
