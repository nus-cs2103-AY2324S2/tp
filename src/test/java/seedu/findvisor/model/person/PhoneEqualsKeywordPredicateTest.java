package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class PhoneEqualsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "91234567";
        String secondPredicateKeyword = "84382123";

        PhoneEqualsKeywordPredicate firstPredicate = new PhoneEqualsKeywordPredicate(firstPredicateKeyword);
        PhoneEqualsKeywordPredicate secondPredicate = new PhoneEqualsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneEqualsKeywordPredicate firstPredicateCopy = new PhoneEqualsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneEqualsKeyword_returnsTrue() {
        PhoneEqualsKeywordPredicate predicate = new PhoneEqualsKeywordPredicate("91234567");
        assertTrue(predicate.test(new PersonBuilder().withPhone("91234567").build()));
    }

    @Test
    public void test_phoneDoesNotEqualKeywords_returnsFalse() {
        // Non-matching keyword
        PhoneEqualsKeywordPredicate predicate = new PhoneEqualsKeywordPredicate("91234567");
        assertFalse(predicate.test(new PersonBuilder().withPhone("84382123").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "example@example.com";
        PhoneEqualsKeywordPredicate predicate = new PhoneEqualsKeywordPredicate(keyword);
        String expected = PhoneEqualsKeywordPredicate.class.getCanonicalName() + "{phone=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
