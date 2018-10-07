package com.montesinnos.friendly.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Prints the date provided as yyyy-MM-dd
     * @param date to be printed
     * @return String
     */
    public static String getTimestamp(final Date date) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }


    /**
     * Prints the current date as yyyy-MM-dd
     * @return String
     */
    public static String getTimestamp() {
        return getTimestamp(new Date());
    }
}
