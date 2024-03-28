package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdEqualsPredicateTest {

    @Test
    public void equals() {
        String firstId = "100001";
        String secondId = "100002";

        IdEqualsPredicate firstPredicate = new IdEqualsPredicate(firstId);
        IdEqualsPredicate secondPredicate = new IdEqualsPredicate(secondId);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdEqualsPredicate firstPredicateCopy = new IdEqualsPredicate(firstId);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different id -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idEquals_returnsTrue() {
        // Correct ID within specified constraints and converting String to Integer
        String validIdString = "100001";
        int validIdInt = Integer.parseInt(validIdString);
        IdEqualsPredicate predicate = new IdEqualsPredicate(validIdString);
        assertTrue(predicate.test(new PersonBuilder().withId(validIdInt).build()));
    }

    @Test
    public void test_idDoesNotEqual_returnsFalse() {
        String validIdString = "100001";

        // Different ID, still within the valid range
        IdEqualsPredicate predicate = new IdEqualsPredicate(validIdString);
        assertFalse(predicate.test(new PersonBuilder().withId(100002).build()));
    }
}
