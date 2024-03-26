package seedu.edulink.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }

    //---------------- Tests for matchesIgnoreCase -------------------------------------
    @Test
    public void matchesIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
                .matchesIgnoreCase("sentence", null));

        assertThrows(NullPointerException.class, () -> StringUtil
                .matchesIgnoreCase(null, "sentence"));
    }

    @Test
    public void matchesIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Sentence parameter cannot be empty", ()
                -> StringUtil.matchesIgnoreCase("sentence", "  "));
    }

    @Test
    public void matchesIgnoreCase_partialMatch_returnsTrue() {
        assertFalse(StringUtil.matchesIgnoreCase("Smart", "t"));
        assertFalse(StringUtil.matchesIgnoreCase("Smart", "Smar"));
        assertFalse(StringUtil.matchesIgnoreCase("Smart", "mart"));
        assertFalse(StringUtil.matchesIgnoreCase("Smart", "mar"));
    }

    @Test
    public void matchesIgnoreCase_fullMatch_returnsTrue() {
        assertTrue(StringUtil.matchesIgnoreCase("Smart", "Smart"));
    }

    @Test
    public void matchesIgnoreCase_noMatch_returnsFalse() {
        assertFalse(StringUtil.matchesIgnoreCase("Smart", "B"));
        assertFalse(StringUtil.matchesIgnoreCase("Smart", "Smarter"));
    }


    //---------------- Tests for containsIgnoreCase -------------------------------------
    @Test
    public void containsIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
            .containsIngnoreCase("typical sentence", null));
    }

    @Test
    public void containsIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsIngnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsIngnoreCase(null, "abc"));
    }

    @Test
    public void containsIgnoreCase_partialMatch_returnsTrue() {
        assertTrue(StringUtil.containsIngnoreCase("A1234567X", "X"));
        assertTrue(StringUtil.containsIngnoreCase("A1234567X", "123"));
        assertTrue(StringUtil.containsIngnoreCase("A1234567X", "A"));
        assertTrue(StringUtil.containsIngnoreCase("A1234567X", "a123"));
    }

    @Test
    public void containsIgnoreCase_fullMatch_returnsTrue() {
        assertTrue(StringUtil.containsIngnoreCase("A1234567X", "A1234567X"));
    }

    @Test
    public void containsIgnoreCase_noMatch_returnsFalse() {
        assertFalse(StringUtil.containsIngnoreCase("A1234567X", "B"));
        assertFalse(StringUtil.containsIngnoreCase("A1234567X", "A1234567Y"));
    }

    //---------------- Tests for containsOrderedSubstringIgnoreCase -------------------------------------


    /*
     * Invalid equivalence partitions for word: null, empty
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsOrderedSubstringIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil
            .containsOrderedSubstringIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsOrderedSubstringIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsOrderedSubstringIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - multiple words
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *   - matches the first few characters of any words
     *
     * Possible scenarios returning false:
     *   - Does not match does the search word chronologically. e.g. ABC & BC will return false.
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsOrderedSubstringIgnoreCase_partialMatch_returnsTrue() {
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("Johnathan Doe", "John"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("Johnathan Oliver", "Oli"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("Johnathan Oliver", "J"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("Johnathan Oliver", "O"));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_oneWordMatch_returnsTrue() {
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "John"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "Doe"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("John Doe Li", "Li"));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_combinedWordsMath_returnsTrue() {
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "John Doe"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "John Do"));
        assertTrue(StringUtil.containsOrderedSubstringIgnoreCase("John Doe Li", "Doe Li"));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_moreDetails_returnsFalse() {
        assertFalse(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "Johnathan Doe"));
        assertFalse(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "John Does"));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_invalidOrdering_returnsFalse() {
        assertFalse(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "Doe John"));
        assertFalse(StringUtil.containsOrderedSubstringIgnoreCase("Johnathan Doe", "nathan"));
        assertFalse(StringUtil.containsOrderedSubstringIgnoreCase("Johnathan Oliver", "liver"));
    }

    @Test
    public void containsOrderedSubstringIgnoreCase_queryWordsGreaterThanSentence_returnsFalse() {
        assertFalse(StringUtil.containsOrderedSubstringIgnoreCase("John Doe", "John Doe Mike"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
