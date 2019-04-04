package com.montesinnos.friendly.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringRemoverTest {

    @Test
    void removeTest() {
        final String[] words = {"ugly", "evil"};
        final StringRemover remover = new StringRemover(words);

        assertEquals("", remover.remove(""));
        assertEquals("The Penny is and", remover.remove("The Penny is ugly and evil"));
        assertEquals("The Penny is ugly-evil", remover.remove("The Penny is ugly-evil"));
    }

    @Test
    void shouldRemoveTest() {
        final String[] words = {"ugly", "evil"};
        final StringRemover remover = new StringRemover(words);

        assertFalse(remover.shouldRemove("Penny"));
        assertTrue(remover.shouldRemove("evil"));
    }
}