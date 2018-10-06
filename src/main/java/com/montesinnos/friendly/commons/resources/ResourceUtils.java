package com.montesinnos.friendly.commons.resources;

import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.montesinnos.friendly.commons.file.TextFileUtils.CHARSET;

public class ResourceUtils {

    public static List<String> readLines(final String path) {
        try {
            return Resources.readLines(getUrl(path), CHARSET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(final String path) {
        try {
            return Resources.toString(getUrl(path), CHARSET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getUrl(final String path) {
        final URL url = Resources.getResource(path);
        return url;
    }

    public static Path getPath(final String path) {
        return Paths.get(Resources.getResource(path).getPath());
    }
}
