package com.montesinnos.friendly.commons.file;

import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {
    /**
     * Walks through the directory and returns all files, including those in subdirectories.
     * You can pass an extension filter
     *
     * @param path      to be walked
     * @param extension to be filtered. Add a '.' to have it exact
     * @return Seq of file names
     */
    public static List<Path> getFiles(final String path, final String extension) {
        return getFiles(Paths.get(path), extension);
    }

    /**
     * Walks through the directory and returns all files, including those in subdirectories.
     * You can pass an extension filter
     *
     * @param path      to be walked
     * @param extension to be filtered. Add a '.' to have it exact
     * @return Seq of file names
     */
    public static List<Path> getFiles(final Path path, final String extension) {
        try {
            return Files.walk(path)
                    .filter(Files::isRegularFile)
                    .filter(x -> !x.getFileName().toString().startsWith("."))
                    .filter(x -> !x.toString().startsWith("."))
                    .filter(x -> Strings.isBlank(extension) || x.toString().endsWith(extension))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Walks through the directory and returns all files, including those in subdirectories.
     *
     * @param path to be walked
     * @return Seq of file names
     */
    public static List<Path> getFiles(final String path) {
        return getFiles(Paths.get(path), "");
    }

    /**
     * Walks through the directory and returns all files, including those in subdirectories.
     *
     * @param path to be walked
     * @return Seq of file names
     */
    public static List<Path> getFiles(final Path path) {
        return getFiles(path, "");
    }


    public static Path move(final Path source, final Path to) {
        try {
            return Files.move(source, to);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Path createTempDir(final String prefix) {
        try {
            return Files.createTempDirectory(prefix);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Path createTempDir() {
        return createTempDir("temp-from-java-app-");
    }

    public static Path rename(final Path source, final String newName) {
        return move(source, source.resolveSibling(newName));
    }

    public static Path renameFileExtension(final Path path, final String newExtension) {
        return rename(path, getFileNameWithoutExtension(path) + '.' + newExtension);
    }

    public static Path renameFileExtension(final String path, final String newExtension) {
        return renameFileExtension(Paths.get(path), newExtension);
    }

    public static String getFileNameWithNewExtension(final String fileName, final String newExtension) {
        return getFileNameWithoutExtension(fileName) + '.' + newExtension;
    }

    public static Path getFileNameWithNewExtension(final Path path, final String newExtension) {
        return path.resolveSibling(getFileNameWithoutExtension(path) + '.' + newExtension);
    }

    public static String getExtension(final Path path) {
        return getExtension(path.toString());
    }

    public static String getExtension(final String path) {
        return com.google.common.io.Files.getFileExtension(path);
    }

    public static String getFileNameWithoutExtension(final String path) {
        return com.google.common.io.Files.getNameWithoutExtension(path);
    }

    public static String getFileNameWithoutExtension(final Path path) {
        return getFileNameWithoutExtension(path.toString());
    }

    public static long getSize(final String path) {
        return getSize(Paths.get(path));
    }

    public static long getSize(final Path path) {
        return getFiles(path)
                .stream()
                .mapToLong(file ->
                {
                    try {
                        return Files.size(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })
                .sum();
    }

    public static boolean exists(final Path path) {
        return Files.exists(path);
    }


    public static Path createDir(final Path path) {
        try {
            return Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Path createParent(final Path path) {
        return createDir(path.getParent());
    }
}
