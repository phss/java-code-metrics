package com.phsshp.file;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class FileCache {

    public File getByAbsolutePath(String path) throws NoSuchFileException {
        throw new NoSuchFileException(path);
    }
}
