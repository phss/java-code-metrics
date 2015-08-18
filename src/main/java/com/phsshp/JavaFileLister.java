package com.phsshp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.lang.String.format;

public class JavaFileLister {

    public List<File> list(String path) throws FileNotFoundException {
        List<File> files = new ArrayList<>();
        Stack<File> filesToProcess = new Stack<>();
        filesToProcess.push(fileAt(path));
        while (!filesToProcess.empty()) {
            File file = filesToProcess.pop();
            if (file.getAbsolutePath().endsWith(".java")) {
                files.add(file);
            }
            if (file.isDirectory()) {
                for (File nextFile : file.listFiles()) {
                    filesToProcess.push(nextFile);
                }
            }
        }
        return files;
    }

    private File fileAt(String path) throws FileNotFoundException {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        throw new FileNotFoundException(format("Path '%s' not found", path));
    }
}
