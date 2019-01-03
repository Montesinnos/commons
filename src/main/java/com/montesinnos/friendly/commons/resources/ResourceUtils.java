package com.montesinnos.friendly.commons.resources;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.montesinnos.friendly.commons.file.FileUtils;
import com.montesinnos.friendly.commons.file.TextFileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceUtils {

    /**
     * Reads given resource file as a string.
     *
     * @param path the path to the resource file
     * @return the file's contents or null if the file could not be opened
     */
    public static Stream<String> readLinesJava8(final String path) {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final InputStream is = classLoader.getResourceAsStream(path);
        if (is != null) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines();
        }
        throw new IllegalArgumentException("Resource can't find found: " + path);
    }

    /**
     * Reads given resource file as a string.
     *
     * @param path the path to the resource file
     * @return the file's contents or null if the file could not be opened
     */
    public static String readJava8(final String path) {
        return readLinesJava8(path).collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Reads all lines in a resource file
     *
     * @param path relative resource path string
     * @return a list of strings with the content of the file
     */
    public static List<String> readLines(final String path) {
        try {
            return Arrays.asList(Resources.toString(getResourceUrl(path), Charsets.UTF_8)
                    .split("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TextFileUtils.readLines(getPath(path));
    }

    /**
     * Reads the entire content of a resource file as String
     *
     * @param path relative resource path string
     * @return a string with all the content of the file
     */
    public static String read(final String path) {
        try {
            return readJava8(path);
        } catch (final IllegalArgumentException e) {
            //meh. Couldn't find the file this way. Let's try another
        }
        try {
            return Resources.toString(getResourceUrl(path), Charsets.UTF_8);
        } catch (final IllegalArgumentException e) {
            //meh. Couldn't find the file this way. Let's try another
        } catch (IOException e) {
            //meh. Couldn't find the file this way. Let's try another
        }
        return TextFileUtils.read(getPath(path));
    }

    /**
     * Gets a path to a given resources
     * TODO test if the path is actually correct and attempt a different way to find it
     *
     * @param path relative resource path string
     * @return the absolute path of the resource
     */
    public static Path getPath(final String path) {
        try {
            final Path path1 = Paths.get(Resources.getResource(path).getPath()).toAbsolutePath();
            if (FileUtils.exists(path1)) {
                return path1;
            }
        } catch (final IllegalArgumentException e) {
            //meh. Couldn't find the file this way. Let's try another
        }
        return getPathFromClassLoader(path);
    }

    public static Path getPathFromClassLoader(final String path) {
        return Paths.get(ClassLoader.getSystemClassLoader().getResource(path).getPath()).toAbsolutePath();
    }

    public static URL getResourceUrl(final String path) {
        final URL url = Resources.getResource(path);
        return url;
    }
}
