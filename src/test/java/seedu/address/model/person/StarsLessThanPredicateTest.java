package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StarsLessThanPredicateTest {
    @Test
    public void equals() {

        StarsLessThanPredicate firstPredicate = new StarsLessThanPredicate(1);
        StarsLessThanPredicate secondPredicate = new StarsLessThanPredicate(2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StarsLessThanPredicate firstPredicateCopy = new StarsLessThanPredicate(1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_starsLessThan_returnsTrue() {
        StarsLessThanPredicate predicate = new StarsLessThanPredicate(1);
        assertTrue(predicate.test(new PersonBuilder().withStar(0).build()));
    }

    @Test
    public void test_starsLessThan_returnsFalse() {
        StarsLessThanPredicate predicate = new StarsLessThanPredicate(1);
        assertFalse(predicate.test(new PersonBuilder().withStar(1).build()));
    }

    @Test
    public void toStringMethod() {
        StarsLessThanPredicate predicate = new StarsLessThanPredicate(1);
        String expected = StarsLessThanPredicate.class.getCanonicalName() + "{upper bound: " + "=1}";
        assertEquals(expected, predicate.toString());
    }
}
