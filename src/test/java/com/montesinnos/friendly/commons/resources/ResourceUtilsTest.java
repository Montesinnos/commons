package com.montesinnos.friendly.commons.resources;

import org.junit.jupiter.api.Test;

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
    }
}