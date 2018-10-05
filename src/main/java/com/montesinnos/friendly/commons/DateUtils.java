package com.montesinnos.friendly.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getTimestamp(final Date date) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String getTimestamp() {
        return getTimestamp(new Date());
    }
}
