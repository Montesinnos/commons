package com.montesinnos.friendly.commons.parsers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.base.CharMatcher;
import com.montesinnos.friendly.commons.file.FileUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.montesinnos.friendly.commons.file.TextFileUtils.CHARSET;

public class CsvParser {
    /**
     * Converts a CSV file to a Json file
     *
     * @param csv  path to the CSV
     * @param json path to the output Json file
     * @return the output path (same as input)
     */
    public static Path toJson(final String csv, final String json) {
        return toJson(Paths.get(csv), Paths.get(json));
    }

    /**
     * Converts a CSV file to a Json file
     *
     * @param csv  path to the CSV
     * @param json path to the output Json file
     * @return the output path (same as input)
     */
    public static Path toJson(final Path csv, final Path json) {
        FileUtils.createParent(json);
        final ObjectWriter writer = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY).writer();

        final CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        final CsvMapper csvMapper = new CsvMapper();
        try (final BufferedWriter bw = Files.newBufferedWriter(json, CHARSET)) {
            final MappingIterator<Object> it = csvMapper.readerFor(Map.class).with(csvSchema).readValues(csv.toFile());
            while (it.hasNextValue()) {
                bw.write(writer.writeValueAsString(it.nextValue()));
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Splits a CSV string in a list of values
     *
     * @param line CSV String to be split
     * @return new List of values
     */
    public static List<String> split(final String line) {
        return Arrays.stream(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))
                .map(x -> CharMatcher.anyOf("\" ").trimFrom(x))
                .collect(Collectors.toList());
    }
}
