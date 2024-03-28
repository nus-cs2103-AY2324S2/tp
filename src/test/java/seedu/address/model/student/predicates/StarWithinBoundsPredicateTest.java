package seedu.address.model.student.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StarWithinBoundsPredicateTest {
    @Test
    public void equals() {

        StarWithinBoundsPredicate firstPredicate = new StarWithinBoundsPredicate("<", 1);
        StarWithinBoundsPredicate secondPredicate = new StarWithinBoundsPredicate("<", 2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StarWithinBoundsPredicate firstPredicateCopy = new StarWithinBoundsPredicate("<", 1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_starWithinBounds_returnsTrue() {
        StarWithinBoundsPredicate predicate = new StarWithinBoundsPredicate("<", 1);
        assertTrue(predicate.test(new StudentBuilder().withStar(0).build()));
    }

    @Test
    public void test_starWithinBounds_returnsFalse() {
        StarWithinBoundsPredicate predicate = new StarWithinBoundsPredicate("<", 1);
        assertFalse(predicate.test(new StudentBuilder().withStar(1).build()));
    }

    @Test
    public void toStringMethod() {
        StarWithinBoundsPredicate predicate = new StarWithinBoundsPredicate("<", 1);
        String expected = StarWithinBoundsPredicate.class.getCanonicalName() + "{value" + "=1}";
        assertEquals(expected, predicate.toString());
    }
}
