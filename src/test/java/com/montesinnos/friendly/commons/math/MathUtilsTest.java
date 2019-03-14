package com.montesinnos.friendly.commons.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathUtilsTest {

    @Test
    void roundTest() {
        assertEquals(123.457, MathUtils.round(123.45678, 3));
        assertEquals(123.456, MathUtils.round(123.45644, 3));
        assertEquals("123.457", MathUtils.round(123.45678, 3) + "");
        assertEquals(1.000, MathUtils.round(1, 3));
    }
}