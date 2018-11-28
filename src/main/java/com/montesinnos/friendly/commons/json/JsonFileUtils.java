package com.montesinnos.friendly.commons.json;

import com.montesinnos.friendly.commons.file.TextFileUtils;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.montesinnos.friendly.commons.file.TextFileUtils.LINE_SEPARATOR;

/**
 * Manipulates files containing Json documents. It's a bridge between {@link JsonUtils}
 * and {@link TextFileUtils}
 */
public class JsonFileUtils {

    /**
     * Writes all Objects as Json lines to the provided path
     *
     * @param path  to be written
     * @param jsons objects to be written
     * @return the path written
     */
    public static Path writeJsonsToFile(final Path path, Collection<Object> jsons) {
        TextFileUtils.write(
                path,
                jsons.stream()
                        .map(JsonUtils::toJson)
                        .collect(Collectors.joining(LINE_SEPARATOR))

        );
        return path;
    }
}
