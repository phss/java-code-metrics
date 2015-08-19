package com.phsshp.file;

import org.junit.Test;

import java.nio.file.NoSuchFileException;

public class FileCacheTest {

    @Test(expected = NoSuchFileException.class)
    public void throwsExceptionIfFileNotInCache() throws Exception {
        new FileCache().getByAbsolutePath("src/no-such-file.txt");
    }
}