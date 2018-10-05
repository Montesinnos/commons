package com.montesinnos.friendly.commons.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilsTest {


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
}