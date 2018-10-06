package com.montesinnos.friendly.commons.json;

import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {
    final Path path = ResourceUtils.getPath("json");
    final String testJson = "{\"phone_number\": \"+1 323-462-5890\", \"crawl_date\": \"2017-06-19 16:16:42.585850\", \"review_count\": \"504\"}";

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

}