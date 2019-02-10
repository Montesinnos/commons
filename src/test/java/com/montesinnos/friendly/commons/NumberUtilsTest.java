package com.montesinnos.friendly.commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberUtilsTest {

    @Test
    void isOnlyDigitsTest() {
        assertTrue(NumberUtils.isOnlyDigits("0123456789"));
        assertTrue(NumberUtils.isOnlyDigits(" 0 0 2 3"));
        assertFalse(NumberUtils.isOnlyDigits("123456789a"));
    }
}