package com.montesinnos.friendly.commons.text;

import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static List<String> split(final String line) {
        final List<String> result = new ArrayList<>();
        int start = 0;
        boolean inQuotes = false;
        for (int current = 0; current < line.length(); current++) {
            if (line.charAt(current) == '\"') inQuotes = !inQuotes; // toggle state
            boolean atLastChar = (current == line.length() - 1);
            if (atLastChar) result.add(line.substring(start));
            else if (line.charAt(current) == ',' && !inQuotes) {
                result.add(line.substring(start, current).replaceAll("^\"|\"$", ""));
                start = current + 1;
            }
        }
        return result;
    }
}
