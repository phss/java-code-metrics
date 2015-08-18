package com.phsshp;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileListerTest {

    @Test
    public void listInexistentDirectoryReturnEmptyList() {
        FileLister fileLister = new FileLister();

        assertThat(fileLister.list("src/test/resources/nosuchdir"), is(empty()));
    }

    @Test
    public void listSingleFile() {
        FileLister fileLister = new FileLister();

        assertThat(fileLister.list("src/test/resources/test-project/SomeFile.java"), contains(new File("src/test/resources/test-project/SomeFile.java")));
    }

}