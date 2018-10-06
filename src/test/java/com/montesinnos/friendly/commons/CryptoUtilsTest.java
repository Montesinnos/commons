package com.montesinnos.friendly.commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoUtilsTest {

    @Test
    void md5Test() {
        assertEquals("7dbbcee180ba4d456e4aa1cfbdad9c7b", CryptoUtils.md5("this is a secret"));
    }
}