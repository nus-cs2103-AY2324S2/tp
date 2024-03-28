package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagEqualsPredicateTest {

    @Test
    public void equals() {
        String firstTag = "intern";
        String secondTag = "general";

        TagEqualsPredicate firstPredicate = new TagEqualsPredicate(firstTag);
        TagEqualsPredicate secondPredicate = new TagEqualsPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagEqualsPredicate firstPredicateCopy = new TagEqualsPredicate(firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different year -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagEquals_returnsTrue() {
        // Exact year match
        TagEqualsPredicate predicate = new TagEqualsPredicate("intern");
        assertTrue(predicate.test(new PersonBuilder().withTags("intern").build()));
    }

    @Test
    public void test_tagDoesNotEqual_returnsFalse() {
        // Different year
        TagEqualsPredicate predicate = new TagEqualsPredicate("intern");
        assertFalse(predicate.test(new PersonBuilder().withTags("general").build()));
    }
}
