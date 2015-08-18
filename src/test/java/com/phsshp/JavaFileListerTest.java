package com.phsshp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class JavaFileListerTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private final JavaFileLister javaFileLister = new JavaFileLister();

    @Test
    public void throwExceptionIfFileInPathNotFound() throws Exception {
        exception.expect(FileNotFoundException.class);
        exception.expectMessage("Path 'src/test/resources/nosuchdir' not found");

        javaFileLister.list("src/test/resources/nosuchdir");
    }

    @Test
    public void listSingleFile() throws Exception {
        assertThat(javaFileLister.list("src/test/resources/test-project/SomeFile.java"), contains(
                new File("src/test/resources/test-project/SomeFile.java")));
    }

    @Test
    public void emptyListForNonJavaFile() throws Exception {
        assertThat(javaFileLister.list("src/test/resources/test-project/SomeFile.class"), is(empty()));
    }

    @Test
    public void listAllJavaFilesInADirectory() throws Exception {
        assertThat(javaFileLister.list("src/test/resources/test-project"), containsInAnyOrder(
                new File("src/test/resources/test-project/SomeFile.java"),
                new File("src/test/resources/test-project/pkg1/AnotherInPackage1.java"),
                new File("src/test/resources/test-project/pkg1/InPackage1.java"),
                new File("src/test/resources/test-project/pkg2/InPackage2.java")));
    }

}