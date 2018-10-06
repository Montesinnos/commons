package com.montesinnos.friendly.commons.file;

import org.apache.logging.log4j.util.Strings;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class TextFileUtils {
    public final static Charset CHARSET = StandardCharsets.UTF_8;

    public static String read(final String path) {
        return read(Paths.get(path));
    }

    public static String read(final Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String readFirstLine(final Path path) {
        try {
            return Files.lines(path)
                    .findFirst()
                    .orElse("");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static List<String> readLines(final String path) {
        return readLines(Paths.get(path));
    }

    public static List<String> readLines(final Path path) {
        try {
            return Files.readAllLines(path, CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> streamLines(final Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static long countLines(final String path) {
        return countLines(Paths.get(path));
    }


    public static long countLines(final Path path) {
        try {
            return Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static long countLinesFromAllFiles(final Path path, final String extension) {
        return FileUtils
                .getFiles(path, extension)
                .stream()
                .mapToLong(TextFileUtils::countLines)
                .sum();
    }

    public static long countLinesFromAllFiles(final Path path) {
        return countLinesFromAllFiles(path, "");
    }


    public static long countNonBlankLines(final Path path) {
        try {
            return Files.lines(path)
                    .filter(Strings::isNotBlank)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Path write(final Path path, final String string) {
        try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(path, CHARSET)) {
            bufferedWriter.write(string);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return path;
    }
}
