package seedu.address.model.student.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class BoltWithinBoundsPredicateTest {
    @Test
    public void equals() {

        BoltWithinBoundsPredicate firstPredicate = new BoltWithinBoundsPredicate("<", 1);
        BoltWithinBoundsPredicate secondPredicate = new BoltWithinBoundsPredicate("<", 2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BoltWithinBoundsPredicate firstPredicateCopy = new BoltWithinBoundsPredicate("<", 1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_boltWithinBounds_returnsTrue() {
        BoltWithinBoundsPredicate predicate = new BoltWithinBoundsPredicate("<", 1);
        assertTrue(predicate.test(new StudentBuilder().withBolt(0).build()));
    }

    @Test
    public void test_boltWithinBounds_returnsFalse() {
        BoltWithinBoundsPredicate predicate = new BoltWithinBoundsPredicate("<", 1);
        assertFalse(predicate.test(new StudentBuilder().withBolt(1).build()));
    }

    @Test
    public void toStringMethod() {
        BoltWithinBoundsPredicate predicate = new BoltWithinBoundsPredicate("<", 1);
        String expected = BoltWithinBoundsPredicate.class.getCanonicalName() + "{value" + "=1}";
        assertEquals(expected, predicate.toString());
    }
}
