package com.phsshp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class JavaFileLister {

    public List<File> list(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.exists()) {
            if (file.getAbsolutePath().endsWith(".java")) {
                return Arrays.asList(file);
            }
            return new ArrayList<>();
        }
        throw new FileNotFoundException(format("Path '%s' not found", path));
    }
}
