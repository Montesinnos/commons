package com.montesinnos.friendly.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Prints the date provided as yyyy-MM-dd
     *
     * @param date to be printed
     * @return String
     */
    public static String getTimestamp(final Date date) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }


    /**
     * Prints the date provided as yyyyMMdd_hhmmss
     *
     * @param date to be printed
     * @return String
     */
    public static String getTimestampLong(final Date date) {
        final DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
        return df.format(date);
    }

    /**
     * Prints the date provided as yyyyMMdd_hhmmss
     *
     * @return String
     */
    public static String getTimestampLong() {
        return getTimestampLong(new Date());
    }

    /**
     * Prints the current date as yyyy-MM-dd
     *
     * @return String
     */
    public static String getTimestamp() {
        return getTimestamp(new Date());
    }

    /**
     * Prints the date provided as yyyy-MM-dd-hh-mm-ss-S
     *
     * @return String
     */
    public static String getVerySpecificTimestamp() {
        return getVerySpecificTimestamp(new Date());
    }

    /**
     * Prints the date provided as yyyy-MM-dd-hh-mm-ss-S
     *
     * @param date to be printed
     * @return String
     */
    public static String getVerySpecificTimestamp(final Date date) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-S");
        return df.format(date);
    }
}
