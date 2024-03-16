package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class PhoneContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "91234567";
        String secondPredicateKeyword = "84382123";

        PhoneContainsKeywordPredicate firstPredicate = new PhoneContainsKeywordPredicate(firstPredicateKeyword);
        PhoneContainsKeywordPredicate secondPredicate = new PhoneContainsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordPredicate firstPredicateCopy = new PhoneContainsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeyword_returnsTrue() {
        // Exact match
        PhoneContainsKeywordPredicate predicate = new PhoneContainsKeywordPredicate("91234567");
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));

        predicate = new PhoneContainsKeywordPredicate("9123");
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));
    }

    @Test
    public void test_phoneDoesNotContainsKeyword_returnsFalse() {
        // Non-matching keyword
        PhoneContainsKeywordPredicate predicate = new PhoneContainsKeywordPredicate("91234567");
        assertFalse(predicate.test(new PersonBuilder().withPhone("84382123").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "91234567";
        PhoneContainsKeywordPredicate predicate = new PhoneContainsKeywordPredicate(keyword);
        String expected = PhoneContainsKeywordPredicate.class.getCanonicalName() + "{phone=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
