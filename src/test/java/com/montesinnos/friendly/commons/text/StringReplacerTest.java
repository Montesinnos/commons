package com.montesinnos.friendly.commons.text;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringReplacerTest {

    @Test
    void replaceTest() {
        final Map<String, String> map = new HashMap<>();
        map.put("penny", "The Penny");
        map.put("DOG", "PUPPY");
        final StringReplacer replacer = new StringReplacer(map);

        assertEquals("", replacer.replace(""));
        assertEquals("I walk The Penny everyday", replacer.replace("I walk penny everyday"));
        assertEquals("The Penny is a PUPPY", replacer.replace("penny is a dog"));
        assertEquals("ThePenny is tiny", replacer.replace("ThePenny is tiny"));
    }

    @Test
    void replaceWordTest() {
        final Map<String, String> map = new HashMap<>();
        map.put("penny", "The Penny");
        map.put("DOG", "PUPPY");
        final StringReplacer replacer = new StringReplacer(map);
        assertEquals("", replacer.replaceWord(""));
        assertEquals("The Penny", replacer.replaceWord("penny"));
    }
}