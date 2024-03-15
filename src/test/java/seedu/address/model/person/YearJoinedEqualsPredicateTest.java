package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class YearJoinedEqualsPredicateTest {

    @Test
    public void equals() {
        String firstYear = "2010";
        String secondYear = "2015";

        YearJoinedEqualsPredicate firstPredicate = new YearJoinedEqualsPredicate(firstYear);
        YearJoinedEqualsPredicate secondPredicate = new YearJoinedEqualsPredicate(secondYear);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        YearJoinedEqualsPredicate firstPredicateCopy = new YearJoinedEqualsPredicate(firstYear);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different year -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_yearJoinedEquals_returnsTrue() {
        // Exact year match
        YearJoinedEqualsPredicate predicate = new YearJoinedEqualsPredicate("2010");
        assertTrue(predicate.test(new PersonBuilder().withYearJoined("2010").build()));
    }

    @Test
    public void test_yearJoinedDoesNotEqual_returnsFalse() {
        // Different year
        YearJoinedEqualsPredicate predicate = new YearJoinedEqualsPredicate("2010");
        assertFalse(predicate.test(new PersonBuilder().withYearJoined("2015").build()));
    }
}
