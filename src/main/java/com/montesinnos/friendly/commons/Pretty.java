package com.montesinnos.friendly.commons;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Helper functions to make pretty print
 */
public class Pretty {
    private final static NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

    public static String pretty(final long number) {
        return numberFormat.format(number);
    }

    public static String pretty(final int number) {
        return numberFormat.format(number);
    }

    public static String pretty(final double number) {
        return numberFormat.format(number);
    }
}
