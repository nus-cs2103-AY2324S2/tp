package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonLessThanEfficiencyPredicateTest {
    @Test
    public void equals() {
        int firstPredicateThreshold = 20;
        int secondPredicateThreshold = 50;

        PersonLessThanEfficiencyPredicate firstPredicate =
                new PersonLessThanEfficiencyPredicate(firstPredicateThreshold);
        PersonLessThanEfficiencyPredicate secondPredicate =
                new PersonLessThanEfficiencyPredicate(secondPredicateThreshold);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonLessThanEfficiencyPredicate firstPredicateCopy =
                new PersonLessThanEfficiencyPredicate(firstPredicateThreshold);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personLessThanEfficiency_returnsTrue() {
        PersonLessThanEfficiencyPredicate predicate =
                new PersonLessThanEfficiencyPredicate(30);
        assertTrue(predicate.test(new PersonBuilder().withEfficiency("20").build()));
    }

    @Test
    public void test_personEqualsToEfficiency_returnsTrue() {
        PersonLessThanEfficiencyPredicate predicate =
                new PersonLessThanEfficiencyPredicate(30);
        assertTrue(predicate.test(new PersonBuilder().withEfficiency("30").build()));
    }

    @Test
    public void test_personLargerEfficiency_returnsFalse() {
        PersonLessThanEfficiencyPredicate predicate =
                new PersonLessThanEfficiencyPredicate(30);
        assertFalse(predicate.test(new PersonBuilder().withEfficiency("50").build()));
    }

    @Test
    public void toStringMethod() {
        int threshold = 30;
        PersonLessThanEfficiencyPredicate predicate = new PersonLessThanEfficiencyPredicate(threshold);

        String expected = PersonLessThanEfficiencyPredicate.class.getCanonicalName() + "{threshold=" + threshold + "}";
        assertEquals(expected, predicate.toString());
    }
}
