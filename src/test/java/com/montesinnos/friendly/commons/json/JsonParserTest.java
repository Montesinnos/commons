package com.montesinnos.friendly.commons.json;

import com.montesinnos.friendly.commons.resources.ResourceUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    @Test
    void parseTest() {
        final JsonParser<JsonTestPojo> jsonParser = new JsonParser(JsonTestPojo.class);
        final String json = ResourceUtils.read("json/pojo.json");
        final JsonTestPojo pojo = jsonParser.parse(json);

        assertEquals("123", pojo.getId());
        assertEquals(3, pojo.getItems().length);
    }
}