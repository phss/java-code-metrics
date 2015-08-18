package com.phsshp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class FileListerTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private final FileLister fileLister = new FileLister();

    @Test
    public void throwExceptionIfFileInPathNotFound() throws Exception {
        exception.expect(FileNotFoundException.class);
        exception.expectMessage("Path 'src/test/resources/nosuchdir' not found");

        fileLister.list("src/test/resources/nosuchdir");
    }

    @Test
    public void listSingleFile() throws Exception {
        assertThat(fileLister.list("src/test/resources/test-project/SomeFile.java"),
                contains(new File("src/test/resources/test-project/SomeFile.java")));
    }

    @Test
    public void emptyListForNonJavaFile() throws Exception {
        assertThat(fileLister.list("src/test/resources/test-project/SomeFile.class"), is(empty()));
    }


}