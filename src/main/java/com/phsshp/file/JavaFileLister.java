package com.phsshp.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class JavaFileLister {

    private final FileSystem fileSystem;
    private final PathMatcher javaFileMatcher;

    public JavaFileLister() {
        fileSystem = FileSystems.getDefault();
        javaFileMatcher = fileSystem.getPathMatcher("glob:**.java");
    }

    public List<File> list(String path) throws IOException {
        return Files.walk(fileSystem.getPath(path))
                .filter(javaFileMatcher::matches)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}
