package com.phsshp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileLister {

    public List<File> list(String path) {
        File file = new File(path);
        if (file.exists()) {
            return Arrays.asList(file);
        }
        return new ArrayList<>();
    }
}