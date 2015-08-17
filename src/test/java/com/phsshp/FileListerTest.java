package com.phsshp;

import org.junit.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileListerTest {

    @Test
    public void listInexistentDirectoryReturnEmptyList() {
        FileLister fileLister = new FileLister();

        assertThat(fileLister.list("src/test/resources/nosuchdir"), is(empty()));
    }

}