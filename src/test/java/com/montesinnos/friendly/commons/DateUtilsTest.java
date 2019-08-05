package com.montesinnos.friendly.commons;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    @Test
    void getTimestampTest() {
        assertEquals(10, DateUtils.getTimestamp().length());
        assertEquals(10, DateUtils.getTimestamp(new Date()).length());
    }

    @Test
    void getTimestampLongTest() {
        assertEquals(15, DateUtils.getTimestampLong().length());
        assertEquals(15, DateUtils.getTimestamp(new Date()).length());
    }

    @Test
    void getVerySpecificTimestampTest() {
        System.out.println( DateUtils.getVerySpecificTimestamp());
        assertEquals(23, DateUtils.getVerySpecificTimestamp().length());
        assertEquals(23, DateUtils.getVerySpecificTimestamp(new Date()).length());
    }
}