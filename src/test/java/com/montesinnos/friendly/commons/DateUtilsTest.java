package com.montesinnos.friendly.commons;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    @Test
    void getTimestampTest() {
        final String timestamp = DateUtils.getTimestamp();
        assertEquals(10, timestamp.length());
    }

    @Test
    void getTimestampTest2() {
        final String timestamp = DateUtils.getTimestamp(new Date());
        assertEquals(10, timestamp.length());
    }
}