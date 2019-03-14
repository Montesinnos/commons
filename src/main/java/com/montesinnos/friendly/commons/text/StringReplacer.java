package com.montesinnos.friendly.commons.text;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Replaces words in strings based on a provided map
 */
public class StringReplacer {

    private final Map<String, String> replacements;

    public StringReplacer(final Map<String, String> replacements) {
        this.replacements = replacements.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toUpperCase().trim(),
                        entry -> entry.getValue().trim()
                ));
    }

    /**
     * Replaces the provided words in the text
     *
     * @param text to be changed
     * @return new String with words replaced
     */
    public String replace(final String text) {
        return Arrays.stream(text.split(" "))
                .map(word -> replacements.getOrDefault(word.toUpperCase(), word))
                .collect(Collectors.joining(" "));
    }
}
