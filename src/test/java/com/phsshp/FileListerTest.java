package com.phsshp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class FileListerTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private final FileLister fileLister = new FileLister();

    @Test
    public void listInexistentDirectoryReturnEmptyList() throws FileNotFoundException {
        exception.expect(FileNotFoundException.class);
        exception.expectMessage("Path 'src/test/resources/nosuchdir' not found");

        fileLister.list("src/test/resources/nosuchdir");
    }

    @Test
    public void listSingleFile() throws FileNotFoundException {
        assertThat(fileLister.list("src/test/resources/test-project/SomeFile.java"), contains(new File("src/test/resources/test-project/SomeFile.java")));
    }

}