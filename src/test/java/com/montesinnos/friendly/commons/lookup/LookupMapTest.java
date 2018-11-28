package com.montesinnos.friendly.commons.lookup;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LookupMapTest {

    @Test
    void getTest() {
        final List<String> mapInput = new ArrayList<>();
        mapInput.add("Key1\tvalue1");
        mapInput.add("Key2\tvalue2");
        mapInput.add("key3\tvalue3");
        mapInput.add("KEY 4\tvalue4");
        final LookupMap lookupMap = LookupMapFactory.fromListWithSeparator(mapInput, "\t");

        assertEquals(4, lookupMap.size());
        assertEquals("value4", lookupMap.get("key4").get());
    }
}