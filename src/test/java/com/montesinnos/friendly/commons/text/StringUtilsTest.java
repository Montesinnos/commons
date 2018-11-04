package com.montesinnos.friendly.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    void dropLeftTest() {
        assertEquals("hello", StringUtils.dropLeft("!hello"));
        assertEquals("hello", StringUtils.dropLeft("   ! hello"));
        assertEquals("hello", StringUtils.dropLeft("   !! hello", 2));
    }

    @Test
    void dropRightTest() {
        assertEquals("hello", StringUtils.dropRight("hello!"));
        assertEquals("hello", StringUtils.dropRight("   hello !"));
        assertEquals("hello", StringUtils.dropRight("    hello!!", 2));
    }
}