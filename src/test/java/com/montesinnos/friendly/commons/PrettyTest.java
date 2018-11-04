package com.montesinnos.friendly.commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrettyTest {

    @Test
    void prettyTest() {
        assertEquals("123,456,789", Pretty.pretty(123456789));
        assertEquals("123,456,789", Pretty.pretty(123456789L));
        assertEquals("123,456,789.01", Pretty.pretty(123456789.01));
    }
}