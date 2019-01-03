package com.montesinnos.friendly.commons.resources;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceUtilsTest {
    @Test
    void readLinesTest() {
        final List<String> lines = ResourceUtils.readLines("resources/readlines.txt");
        assertEquals(10, lines.size());
    }

    @Test
    void readTest() {
        final String string = ResourceUtils.read("resources/read.txt");
        assertEquals(20, string.length());
    }

    @Test
    void getPathTest() {
        final Path path = ResourceUtils.getPath("resources/path.txt");
        assertTrue(path.toFile().exists());
        assertTrue(path.toString().endsWith("resources/path.txt"));
    }

    @Test
    void getPathFromClassLoaderTest() {
        final Path path = ResourceUtils.getPathFromClassLoader("resources/path.txt");
        assertTrue(path.toFile().exists());
    }

    @Test
    void getResourceUrlTest() {
        final URL url = ResourceUtils.getResourceUrl("resources/path.txt");
        assertTrue(url.toString().endsWith("resources/path.txt"));
        assertTrue(new File(url.getPath()).exists());
    }
}