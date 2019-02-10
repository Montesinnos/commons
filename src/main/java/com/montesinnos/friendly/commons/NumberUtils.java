package com.montesinnos.friendly.commons;

import org.apache.logging.log4j.util.Strings;

/**
 * Helper functions to work with numbers
 */
public class NumberUtils {
    /**
     * Checks if a string only contains numbers. It will return false if blank or null.
     * Spaces are ignored
     *
     * @param in String to be tested
     * @return true if all chars are digits
     */
    public static boolean isOnlyDigits(final String in) {
        if (Strings.isBlank(in)) {
            return false;
        }

        final String s = in.replace(' ', '0').trim();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!('0' <= c && c <= '9')) {
                return false;
            }
        }

        return true;
    }
}
