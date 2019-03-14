package com.montesinnos.friendly.commons.text;

import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helpers to efficiently filter out some words from a string
 * Start by providing a collection of Strings (aka blacklist) to the contruction, then just call
 * {@link StringRemover#remove(String)} to clean strings
 */
public class StringRemover {

    private final Set<String> removals;

    public StringRemover(final Collection<String> removals) {
        this.removals = removals.stream()
                .filter(Strings::isNotBlank)
                .map(word -> word.toUpperCase().trim())
                .collect(Collectors.toSet());
    }

    public StringRemover(final String[] removals) {
        this(Arrays.asList(removals));
    }

    /**
     * Remove the provided words in the text
     *
     * @param text to be changed
     * @return new String with words replaced
     */
    public String remove(final String text) {
        return Arrays.stream(text.split(" "))
                .filter(word -> !removals.contains(word.toUpperCase()))
                .collect(Collectors.joining(" "));
    }
}
