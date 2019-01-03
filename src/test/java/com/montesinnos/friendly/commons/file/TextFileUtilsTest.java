package com.montesinnos.friendly.commons.file;

import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextFileUtilsTest {
    private static Path outputDir;
    final Path path = ResourceUtils.getPath("file").resolve("textfileutils");

    @BeforeAll
    static void setUp() {
        outputDir = FileUtils.createTempDir("test");
    }

    @AfterAll
    static void tearDown() {
        FileUtils.delete(outputDir);
    }

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
    void countNonBlankLinesTest() {
        assertEquals(40, TextFileUtils.countNonBlankLines(path.resolve("count")));
    }

    @Test
    void countLinesTest() {
        assertEquals(10, TextFileUtils.countLines(path.resolve("count").resolve("1.txt")));
        assertEquals(10, TextFileUtils.countLines(path.resolve("count").resolve("1.txt").toString()));
    }

    @Test
    void replaceInFileTest() {
        final Path input = path.resolve("replace");
        final Path output = outputDir.resolve("replace");
        FileUtils.createDir(output);

        final Map<String, String> map = new HashMap<>();
        map.put("a", "replacedA");
        map.put("bb+", "replacedB");
        map.put("findme", "!!FOUND!!");
        TextFileUtils.replaceInFiles(input, output, map);
        assertEquals(3, FileUtils.getFilesStream(output).count());
        assertEquals(1, TextFileUtils.streamAllLines(output).filter(x -> x.contains("!!FOUND!!")).count());

        FileUtils.delete(output);
    }
}