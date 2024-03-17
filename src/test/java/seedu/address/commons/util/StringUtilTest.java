package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

    //---------------- Tests for containsIgnoreCase -------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
                -> StringUtil.containsIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsIgnoreCase(null, "abc"));
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
    public void containsIgnoreCase_partialMatch_returnsTrue() {
        assertTrue(StringUtil.containsIgnoreCase("Johnathan Doe", "John"));
        assertTrue(StringUtil.containsIgnoreCase("Johnathan Oliver", "Oli"));
        assertTrue(StringUtil.containsIgnoreCase("Johnathan Oliver", "J"));
        assertTrue(StringUtil.containsIgnoreCase("Johnathan Oliver", "O"));
    }

    @Test
    public void containsIgnoreCase_oneWordMatch_returnsTrue() {
        assertTrue(StringUtil.containsIgnoreCase("John Doe", "John"));
        assertTrue(StringUtil.containsIgnoreCase("John Doe", "Doe"));
    }
    @Test
    public void containsIgnoreCase_combinedWordsMath_returnsTrue() {
        assertTrue(StringUtil.containsIgnoreCase("John Doe", "John Doe"));
        assertTrue(StringUtil.containsIgnoreCase("John Doe", "John Do"));
    }

    @Test
    public void containsIgnoreCase_moreDetails_returnsFalse() {
        assertFalse(StringUtil.containsIgnoreCase("John Doe", "Johnathan Doe"));
        assertFalse(StringUtil.containsIgnoreCase("John Doe", "John Does"));
    }

    @Test
    public void containsIgnoreCase_invalidOrdering_returnsFalse() {
        assertFalse(StringUtil.containsIgnoreCase("John Doe", "Doe John"));
        assertFalse(StringUtil.containsIgnoreCase("Johnathan Doe", "nathan"));
        assertFalse(StringUtil.containsIgnoreCase("Johnathan Oliver", "liver"));
    }

    @Test
    public void containsIgnoreCase_queryWordsGreaterThanSentence_returnsFalse() {
        assertFalse(StringUtil.containsIgnoreCase("John Doe", "John Doe Mike"));
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
