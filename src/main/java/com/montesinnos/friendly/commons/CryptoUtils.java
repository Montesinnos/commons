package com.montesinnos.friendly.commons;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {
    public static String md5(final String message) {
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(message));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
