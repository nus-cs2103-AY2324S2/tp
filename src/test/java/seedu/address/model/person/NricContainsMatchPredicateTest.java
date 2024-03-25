package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NricContainsMatchPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        NricContainsMatchPredicate firstPredicate = new NricContainsMatchPredicate(firstPredicateKeyword);
        NricContainsMatchPredicate secondPredicate = new NricContainsMatchPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NricContainsMatchPredicate firstPredicateCopy = new NricContainsMatchPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nricContainsKeyword_returnsTrue() {
        // Matches part of start of name
        NricContainsMatchPredicate predicate = new NricContainsMatchPredicate("T012");
        assertTrue(predicate.test(new PersonBuilder().withNric("T0123456A").build()));

        // Matches name completely
        predicate = new NricContainsMatchPredicate("T0123456A");
        assertTrue(predicate.test(new PersonBuilder().withNric("T0123456A").build()));

        // Mixed-case keywords
        predicate = new NricContainsMatchPredicate("t01");
        assertTrue(predicate.test(new PersonBuilder().withName("T0123456A").build()));
    }

    @Test
    public void test_nricDoesNotContainKeyword_returnsFalse() {

        // Non-matching keyword
        NricContainsMatchPredicate predicate = new NricContainsMatchPredicate("T0123456B");
        assertFalse(predicate.test(new PersonBuilder().withName("T0123456A").build()));

        // Keywords match name, but does not match nric
        predicate = new NricContainsMatchPredicate("Alice");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                .withNric("T0123456A").build()));

        // Matches but keyword contains more words
        predicate = new NricContainsMatchPredicate("T0123456A1");
        assertFalse(predicate.test(new PersonBuilder().withNric("T0123456A").build()));
    }

    @Test
    public void test_keywordNullOrEmpty_throwsException() {
        // Empty keyword
        NricContainsMatchPredicate predicate = new NricContainsMatchPredicate("");
        assertThrows(IllegalArgumentException.class, () -> predicate
                .test(new PersonBuilder().withName("Alice").build()));

        assertThrows(NullPointerException.class, () -> new NricContainsMatchPredicate(null));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        NricContainsMatchPredicate predicate = new NricContainsMatchPredicate("keyword1");

        String expected = NricContainsMatchPredicate.class.getCanonicalName() + "{nricPrefixToMatch=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
