package com.montesinnos.friendly.commons.parsers;

import com.montesinnos.friendly.commons.file.FileUtils;
import com.montesinnos.friendly.commons.file.TextFileUtils;
import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvParserTest {
    final Path path = ResourceUtils.getPath("parsers").resolve("csv");

    @Test
    void toJsonTest() {
        final Path csv = path.resolve("csv_sample.csv");
        final Path json = FileUtils.createTempDir("delete-me").resolve("sample.json");
        CsvParser.toJson(csv, json);

        assertEquals(100, TextFileUtils.countLines(json));
    }

    @Test
    void splitTest() {
        final String line = "id, name, person, \"number\", \"negative_number\", float, \"other number\", city, \"other name\", \"last float\"";
        assertThat(CsvParser.split(line))
                .contains("id")
                .contains("name")
                .contains("person")
                .contains("\"number\"")
                .contains("\"negative_number\"")
                .contains("float")
                .contains("\"other number\"")
                .contains("city")
                .contains("\"other name\"")
                .contains("\"last float\"");
    }
}