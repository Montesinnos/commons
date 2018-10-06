package com.montesinnos.friendly.commons.file;

import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    final Path path = ResourceUtils.getPath("file").resolve("fileutils");

    @Test
    void getFilesTest() {
        final Path path = this.path.resolve("getfiles");
        assertEquals(4, FileUtils.getFiles(path).size());
        assertEquals(4, FileUtils.getFiles(path.toString()).size());
        assertEquals(1, FileUtils.getFiles(path, "json").size());
        assertEquals(1, FileUtils.getFiles(path, ".json").size());
        assertEquals(4, FileUtils.getFiles(path, "").size());
    }

    @Test
    void createTempDirTest() {
        final Path tempDir = FileUtils.createTempDir("delete-me-asap");
        assertTrue(FileUtils.exists(tempDir));
    }

    @Test
    void existsTest() {
        assertTrue(FileUtils.exists(ResourceUtils.getPath("file/fileutils/exists/1.txt")));
        assertFalse(FileUtils.exists(Paths.get("there-s-no-way-in-hell-i-exists.com")));
    }

    @Test
    void getFileNameWithNewExtensionTest() {
        assertEquals("file.json", FileUtils.getFileNameWithNewExtension("file.txt", "json"));
        assertEquals(Paths.get("file.json"), FileUtils.getFileNameWithNewExtension(Paths.get("file.txt"), "json"));
    }

    @Test
    void getExtensionTest() {
        assertEquals("txt", FileUtils.getExtension("file.txt"));
        assertEquals("txt", FileUtils.getExtension(Paths.get("file.txt")));
    }

    @Test
    void getSizeTest() {
        assertEquals(20, FileUtils.getSize(path.resolve("size").resolve("1.txt")), 5);
        assertEquals(40, FileUtils.getSize(path.resolve("size")), 5);
    }
}