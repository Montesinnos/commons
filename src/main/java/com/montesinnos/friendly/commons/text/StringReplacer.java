package com.montesinnos.friendly.commons.text;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Replaces words in strings based on a provided map
 * <p>
 * Use the factory to create it, by providing a map of OldValue-NewValue
 */
public class StringReplacer {
    public static class StringReplacerFactory {

        public static StringReplacer create(final Map<String, String> replacements) {
            return new StringReplacer(replacements);
        }
    }

    private final Map<String, String> replacements;

    private StringReplacer(final Map<String, String> replacements) {
        this.replacements = replacements.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toUpperCase().trim(),
                        entry -> entry.getValue().trim(),
                        (s,a) -> s
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
                .map(this::replaceWord)
                .collect(Collectors.joining(" "));
    }

    /**
     * Replaces the provided word in the text
     *
     * @param word to be changed
     * @return new String with word replaced
     */
    public String
    replaceWord(final String word) {
        return replacements.getOrDefault(word.toUpperCase(), word);
    }


}
