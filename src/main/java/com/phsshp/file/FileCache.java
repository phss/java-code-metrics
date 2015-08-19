package com.phsshp.file;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class FileCache {

    private final Map<String, File> absolutePathLookup;

    public FileCache(List<File> files) {
        absolutePathLookup = files.stream().collect(toMap(File::getAbsolutePath, identity()));
    }

    public File getByAbsolutePath(String path) throws NoSuchFileException {
        if (absolutePathLookup.containsKey(path)) {
            return absolutePathLookup.get(path);
        }
        throw new NoSuchFileException(path);
    }
}
