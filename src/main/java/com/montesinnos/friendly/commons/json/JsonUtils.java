package com.montesinnos.friendly.commons.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.montesinnos.friendly.commons.file.FileUtils;
import com.montesinnos.friendly.commons.file.TextFileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonUtils {
    public static ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static String toJson(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getFieldNames(final String json) {
        try {
            return ImmutableList.copyOf(objectMapper.readTree(json).fieldNames());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static List<String> getFieldNames(final Path path) {
        return FileUtils.getFiles(path)
                .stream()
                .flatMap(TextFileUtils::streamLines)
                .flatMap(x -> getFieldNames(x).stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public static Map<String, Long> getFieldCounts(final Path path) {
        return FileUtils.getFiles(path)
                .stream()
                .flatMap(TextFileUtils::streamLines)
                .flatMap(x -> getFieldNames(x).stream())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
    }

    public static String getFieldType(final String json, final String fieldName) {
        final JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(json).get(fieldName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        switch (jsonNode.getNodeType()) {
            case ARRAY:
                break;
            case BINARY:
                break;
            case BOOLEAN:
                break;
            case MISSING:
                break;
            case NULL:
                break;
            case NUMBER:
                break;
            case OBJECT:
                break;
            case POJO:
                break;
            case STRING:
                break;
        }
        return jsonNode.getNodeType().toString();
    }


}

