package com.montesinnos.friendly.commons.file;

import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextFileUtilsTest {
    final Path path = ResourceUtils.getPath("file").resolve("textfileutils");

    @Test
    void readTest() {
        assertEquals(20, TextFileUtils.read(path.resolve("read").resolve("1.txt")).length());
    }

    @Test
    void readFirstLineTest() {
        assertEquals("1", TextFileUtils.readFirstLine(path.resolve("read").resolve("1.txt")));
    }

    @Test
    void readLinesTest() {
        assertEquals(10, TextFileUtils.readLines(path.resolve("read").resolve("1.txt")).size());
        assertEquals(10, TextFileUtils.readLines(path.resolve("read").resolve("1.txt").toString()).size());
    }

    @Test
    void streamLinesTest() {
        assertEquals(10, TextFileUtils.streamLines(path.resolve("read").resolve("1.txt")).count());
    }

    @Test
    void countLinesFromAllFilesTest() {
        assertEquals(50, TextFileUtils.countLinesFromAllFiles(path.resolve("count")));
        assertEquals(10, TextFileUtils.countLinesFromAllFiles(path.resolve("count"), "json"));
    }

    @Test
    void countLinesTest() {
        assertEquals(10, TextFileUtils.countLines(path.resolve("count").resolve("1.txt")));
    }

    @Test
    void countLines1() {
    }

    @Test
    void countNonBlankLines() {
    }

    @Test
    void write() {
    }
}