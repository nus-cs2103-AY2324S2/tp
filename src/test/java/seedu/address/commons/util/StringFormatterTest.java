package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringFormatterTest {

    @Test
    public void capitalizeWords_nullInput_throwsNullPointerException() {
        assertEquals(null, StringFormatter.capitalizeWords(null));
    }

    @Test
    public void capitalizeWords_emptyString_emptyStringReturned() {
        assertEquals("", StringFormatter.capitalizeWords(""));
    }

    @Test
    public void capitalizeWords_onlySpaces_emptyStringReturned() {
        assertEquals("", StringFormatter.capitalizeWords("   "));
    }

    @Test
    public void capitalizeWords_singleWord_capitalizedWordReturned() {
        assertEquals("Java", StringFormatter.capitalizeWords("java"));
        assertEquals("3java", StringFormatter.capitalizeWords("3java"));
        assertEquals("#java", StringFormatter.capitalizeWords("#java"));
    }

    @Test
    public void capitalizeWords_multipleWords_allWordsCapitalized() {
        assertEquals("Hello World", StringFormatter.capitalizeWords("hello world"));
        assertEquals("Hello World 3a #home", StringFormatter.capitalizeWords("hello world 3a #home"));
    }

    @Test
    public void capitalizeWords_inputWithExtraSpaces_correctlyCapitalizedWords() {
        assertEquals("Java Is Fun", StringFormatter.capitalizeWords(" java  is   fun "));
        assertEquals("A B C", StringFormatter.capitalizeWords("  a b c ").trim());
    }

    @Test
    public void capitalizeWords_mixedCaseInput_correctlyCapitalizedWords() {
        assertEquals("JaVa IS GReAt", StringFormatter.capitalizeWords("JaVa iS gReAt"));
    }

}
