package com.montesinnos.friendly.commons.math;

public class MathUtils {
    /**
     * Rounds a double to a given number of decimal digits
     *
     * @param num      to be rounded
     * @param decimals number of decimals
     * @return new double
     */
    public static double round(final double num, final int decimals) {
        if (decimals < 0) {
            return 0;
        } else {
            final double factor = Math.pow(10, decimals);
            return Math.round(num * factor) / factor;
        }
    }
}
