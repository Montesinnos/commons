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
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileUtils {
    public final static Charset CHARSET = StandardCharsets.UTF_8;
    public final static String LINE_SEPARATOR = "\n";

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

    public static Stream<String> streamAllLines(final Path path) {
        return FileUtils.getFilesStream(path).flatMap(TextFileUtils::streamLines);
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
                .getFilesStream(path, extension)
                .mapToLong(TextFileUtils::countLines)
                .sum();
    }

    public static long countLinesFromAllFiles(final Path path) {
        return countLinesFromAllFiles(path, "");
    }

    public static long countNonBlankLines(final Path path) {
        return FileUtils
                .getFilesStream(path)
                .flatMap(x -> {
                    try {
                        return Files.lines(x);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Stream.empty();
                })
                .filter(Strings::isNotBlank)
                .count();
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

    /**
     * Replaces all occurrences of provided Strings in all the files in a folder
     *
     * @param path         Input folder
     * @param output       Output folder
     * @param replacements Map of (Find -- Replace) strings
     * @return Output folder path
     */
    public static Path replaceInFiles(final Path path, final Path output, final Map<String, String> replacements) {
        FileUtils.createDir(output);
        FileUtils.getFilesStream(path)
                .forEach(file -> {
                    System.out.println(file);
                    replaceInFile(file,
                            output.resolve(file.getFileName().toString()),
                            replacements);
                });
        return output;
    }


    /**
     * Replaces all occurrences of provided Strings in a single file
     *
     * @param path         Input file
     * @param output       Output file
     * @param replacements Map of (Find -- Replace) strings
     * @return Output file path
     */
    public static Path replaceInFile(final Path path, final Path output, final Map<String, String> replacements) {

        final Map<Pattern, String> compiledReplacement = replacements
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> Pattern.compile(entry.getKey(), Pattern.CASE_INSENSITIVE),
                        Map.Entry::getValue));

        try (final BufferedWriter bufferedWriter = Files.newBufferedWriter(output, CHARSET)) {
            streamLines(path)
                    .forEach(line ->
                    {
                        try {
                            String replaced = line;
                            for (Map.Entry<Pattern, String> entry : compiledReplacement.entrySet()) {
                                replaced = entry.getKey().matcher(replaced).replaceAll(entry.getValue());
                            }
                            bufferedWriter.write(replaced);
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return output;
    }


}