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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.montesinnos.friendly.commons.file.TextFileUtils.CHARSET;

public class CsvParser {
    public static Path toJson(final Path csv, final Path json) {
        FileUtils.createParent(json);
        final ObjectWriter writer = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).writer();

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

    public static List<String> split(final String line) {
        return Arrays.stream(line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"))
                .map(x -> CharMatcher.anyOf("\" ").trimFrom(x))
                .collect(Collectors.toList());
    }
}
