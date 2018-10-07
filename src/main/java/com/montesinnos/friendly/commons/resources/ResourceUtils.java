package com.montesinnos.friendly.commons.resources;

import com.google.common.io.Resources;
import com.montesinnos.friendly.commons.file.FileUtils;
import com.montesinnos.friendly.commons.file.TextFileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ResourceUtils {

    /**
     * Reads all lines in a resource file
     *
     * @param path relative resource path string
     * @return a list of strings with the content of the file
     */
    public static List<String> readLines(final String path) {
        return TextFileUtils.readLines(getPath(path));
    }

    /**
     * Reads the entire content of a resource file as String
     *
     * @param path relative resource path string
     * @return a string with all the content of the file
     */
    public static String read(final String path) {
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
}
