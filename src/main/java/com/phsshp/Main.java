package com.phsshp;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String args[]) throws Exception {
        JavaFileLister fileLister = new JavaFileLister();
        List<File> files = fileLister.list(args[0]);

        System.out.println("file");
        for (File file: files) {
            System.out.println(file.getPath());
        }
    }
}
