package com.phsshp.file;

import org.junit.Test;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FileCacheTest {

    private final File testFile = new File("src/test/resources/test-project/SomeFile.java");
    private final FileCache fileCache = new FileCache(Arrays.asList(testFile));

    @Test(expected = NoSuchFileException.class)
    public void throwsExceptionIfFileNotInCache() throws Exception {
        fileCache.getByAbsolutePath("src/no-such-file.txt");
    }

    @Test
    public void fetchesFileByAbsolutePath() throws Exception {
        File file = fileCache.getByAbsolutePath(testFile.getAbsolutePath());

        assertThat(file, equalTo(testFile));
    }
}