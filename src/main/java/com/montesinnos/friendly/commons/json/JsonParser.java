package com.montesinnos.friendly.commons.json;

import java.io.IOException;

public class JsonParser<T> {

    final Class<T> typeParameterClass;

    public JsonParser(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public T parse(final String json) {
        try {
            return JsonUtils.objectMapper.readValue(json, typeParameterClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
