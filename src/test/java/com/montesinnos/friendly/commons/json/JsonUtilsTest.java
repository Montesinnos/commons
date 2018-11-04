package com.montesinnos.friendly.commons.json;

import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {
    final Path path = ResourceUtils.getPath("json");
    final String testJson = "{\"phone_number\": \"+1 323-462-5890\", \"crawl_date\": \"2017-06-19 16:16:42.585850\", \"review_count\": 504}";

    @Test
    void toJsonTest() {
        final JsonTestPojo jsonTestPojo = new JsonTestPojo();
        jsonTestPojo.setId("123");
        jsonTestPojo.setCreatedAt("2018");
        jsonTestPojo.setUpdatedAt("2019");
        jsonTestPojo.setUserId("montesinnos");
        jsonTestPojo.setItems(new String[]{"1", "2", "3"});

        assertEquals("{\"id\":\"123\",\"created_at\":\"2018\",\"updated_at\":\"2019\",\"user_id\":\"montesinnos\",\"items\":[\"1\",\"2\",\"3\"]}", JsonUtils.toJson(jsonTestPojo));
    }

    @Test
    void getFieldNamesTest() {
        assertThat(JsonUtils.getFieldNames(testJson))
                .contains("phone_number",
                        "crawl_date",
                        "review_count");
    }

    @Test
    void getFieldNamesFromFileTest() {
        assertThat(JsonUtils.getFieldNames(path.resolve("getfieldnames")))
                .contains("id", "created_at", "updated_at", "user_id", "items");
        assertThat(JsonUtils.getFieldNames(path.resolve("getfieldnames").resolve("sample2.json")))
                .contains("id", "items");
    }

    @Test
    void getFieldMapFromFilesTest() {
        assertThat(JsonUtils.getFieldCounts(path.resolve("getfieldnames")))
                .contains(entry("id", 4L),
                        entry("created_at", 3L),
                        entry("updated_at", 3L),
                        entry("user_id", 3L),
                        entry("items", 4L));
    }

    @Test
    void getFieldTypeTest() {
        assertEquals("NUMBER", JsonUtils.getFieldType(testJson, "review_count"));
        assertEquals("STRING", JsonUtils.getFieldType(testJson, "phone_number"));
    }

    @Test
    void appendJsonStringTest() {
        assertEquals("{\"field\": 123, \"field2\": 123}", JsonUtils.appendJsonString("{\"field\": 123 } ", "{\"field2\": 123 } "));
    }

    @Test
    void removeOuterParenthesesTest() {
        assertEquals("\"field\": 123", JsonUtils.removeOuterParentheses("{ \"field\": 123 } "));
    }

    @Test
    void mergeShallowTest() {
        final String json1 = "{\"Key1\": \"Value1\", \"Key2\": \"Value2\"}";
        final String json2 = "{\"Key3\": \"Value3\", \"Key4\": \"Value4\"}";
        assertEquals("{\"Key1\":\"Value1\",\"Key2\":\"Value2\",\"Key3\":\"Value3\",\"Key4\":\"Value4\"}", JsonUtils.mergeShallow(json1, json2));
    }

    @Test
    void mergeTest() {
        final String jsonDeep1 = "{\n" +
                "  \"doc\": {\n" +
                "    \"properties\": {\n" +
                "      \"address\": {\n" +
                "        \"properties\": {\n" +
                "          \"city\": {\n" +
                "            \"type\": \"keyword\"\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        final String jsonDeep2 = "{\n" +
                "  \"doc\": {\n" +
                "    \"properties\": {\n" +
                "      \"source_name\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"address\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"checkins\": {\n" +
                "        \"type\": \"long\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        assertEquals("{\"doc\":{\"properties\":{\"address\":{\"properties\":{\"city\":{\"type\":\"keyword\"}},\"type\":\"keyword\"},\"source_name\":{\"type\":\"keyword\"},\"checkins\":{\"type\":\"long\"}}}}",
                JsonUtils.merge(jsonDeep1, jsonDeep2));
    }
}