package com.montesinnos.friendly.commons.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableList;
import com.montesinnos.friendly.commons.file.FileUtils;
import com.montesinnos.friendly.commons.file.TextFileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.montesinnos.friendly.commons.text.StringUtils.dropLeft;
import static com.montesinnos.friendly.commons.text.StringUtils.dropRight;

public class JsonUtils {
    public static final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

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

    /**
     * Lame function that adds "fields" into an existing Json without actually being a Json. It's just String manipulation
     * It's really, REALLY, silly. But it works. So...
     *
     * @param json          Outer Json
     * @param newObjectJson new inner Json to be added
     * @return new Json containing everything in a friendly fashion
     */
    public static String appendJsonString(final String json, final String newObjectJson) {
        return dropRight(json)
                + ", " +
                removeOuterParentheses(newObjectJson)
                + "}";
    }

    public static String removeOuterParentheses(final String json) {
        return dropLeft(dropRight(json));
    }

    /**
     * Merges 2 Json String. This is a shallow merge and will copy only the first tier of fields
     *
     * @param json1 Json String
     * @param json2 Json String
     * @return new Json String
     */
    public static String mergeShallow(final String json1, final String json2) {
        try {
            ObjectNode node1 = (ObjectNode) objectMapper.readTree(json1);
            ObjectNode node2 = (ObjectNode) objectMapper.readTree(json2);
            JsonNode merged = node1.setAll(node2);

            return objectMapper.writeValueAsString(merged);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds the fields from json2 into json1. json1 has the preference when there's a collision
     *
     * @param json1 Main Json
     * @param json2 Update Json
     * @return New Json String
     */
    public static String merge(final String json1, final String json2) {
        try {
            final JsonNode node1 = objectMapper.readTree(json1);
            final JsonNode node2 = objectMapper.readTree(json2);
            return objectMapper.writeValueAsString(merge(node1, node2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deep copy of JsonNodes.
     * Jackson has this feature since 2.9, but there's 0 documentation from it. Till we figure out this was copy from
     * https://stackoverflow.com/questions/9895041/merging-two-json-documents-using-jackson
     *
     * @param mainNode   Jackson Node
     * @param updateNode Jackson Node
     * @return new Jackson Node
     */
    public static JsonNode merge(final JsonNode mainNode, final JsonNode updateNode) {

        final Iterator<String> fieldNames = updateNode.fieldNames();

        while (fieldNames.hasNext()) {
            String updatedFieldName = fieldNames.next();
            final JsonNode valueToBeUpdated = mainNode.get(updatedFieldName);
            final JsonNode updatedValue = updateNode.get(updatedFieldName);

            // If the node is an @ArrayNode
            if (valueToBeUpdated != null && valueToBeUpdated.isArray() &&
                    updatedValue.isArray()) {
                // running a loop for all elements of the updated ArrayNode
                for (int i = 0; i < updatedValue.size(); i++) {
                    JsonNode updatedChildNode = updatedValue.get(i);
                    // Create a new Node in the node that should be updated, if there was no corresponding node in it
                    // Use-case - where the updateNode will have a new element in its Array
                    if (valueToBeUpdated.size() <= i) {
                        ((ArrayNode) valueToBeUpdated).add(updatedChildNode);
                    }
                    // getting reference for the node to be updated
                    JsonNode childNodeToBeUpdated = valueToBeUpdated.get(i);
                    merge(childNodeToBeUpdated, updatedChildNode);
                }
                // if the Node is an @ObjectNode
            } else if (valueToBeUpdated != null && valueToBeUpdated.isObject()) {
                merge(valueToBeUpdated, updatedValue);
            } else {
                if (mainNode instanceof ObjectNode) {
                    ((ObjectNode) mainNode).replace(updatedFieldName, updatedValue);
                }
            }
        }
        return mainNode;
    }
}

