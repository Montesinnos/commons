package com.montesinnos.friendly.commons.text;

public class StringUtils {

    /**
     * Removes one character from the beginning of the String, skipping all the spaces
     *
     * @param text to be chopped
     * @return new trimmed String
     */
    public static String dropLeft(final String text) {
        return dropLeft(text, 1);
    }

    /**
     * Removes n characters from the beginning of the string, skipping all the spaces
     *
     * @param text      tp be chopped
     * @param charCount how many characters to be chopped
     * @return new trimmed String
     */
    public static String dropLeft(final String text, final int charCount) {
        return text.trim().substring(charCount).trim();
    }

    /**
     * Removes one character from the end of the String, skipping all the spaces
     *
     * @param text to be chopped
     * @return new trimmed String
     */
    public static String dropRight(final String text) {
        return dropRight(text, 1);
    }

    /**
     * Removes n characters from the end of the string, skipping all the spaces
     *
     * @param text      tp be chopped
     * @param charCount how many characters to be chopped
     * @return new trimmed String
     */
    public static String dropRight(final String text, final int charCount) {
        return text.trim().substring(0, text.trim().length() - charCount).trim();
    }
}
