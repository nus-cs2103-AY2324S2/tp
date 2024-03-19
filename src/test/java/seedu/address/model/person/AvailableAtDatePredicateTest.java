package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AvailableAtDatePredicateTest {
    @Test
    public void equals() throws Exception {
        List<String> firstPredicateKeywordList = List.of("01/01/2024");
        List<String> secondPredicateKeywordList = List.of("12/12/2024", "01/01/2024");
        // same object -> returns true
        AvailableAtDatePredicate firstPredicate = new AvailableAtDatePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AvailableAtDatePredicate firstPredicateCopy = new AvailableAtDatePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different dates -> returns false
        AvailableAtDatePredicate secondPredicate = new AvailableAtDatePredicate(secondPredicateKeywordList);
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsAvailableAtDate_returnsTrue() throws Exception {
        // One date
        AvailableAtDatePredicate predicate = new AvailableAtDatePredicate(List.of("01/01/2024"));
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("01/01/2024").build()));

        // Multiple dates
        predicate = new AvailableAtDatePredicate(List.of("12/12/2024"));
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("01/01/2024", "12/12/2024").build()));

        // Only one matching date
        predicate = new AvailableAtDatePredicate(List.of("12/12/2024", "01/01/2024"));
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("01/01/2024").build()));

        predicate = new AvailableAtDatePredicate(List.of("01/01/2024", "12/12/2024"));
        assertTrue(predicate.test(new PersonBuilder().withAvailabilities("12/12/2024").build()));
    }

    @Test
    public void test_doesNotContainAvailableAtDate_returnsFalse() throws Exception {
        // One date
        AvailableAtDatePredicate predicate = new AvailableAtDatePredicate(List.of("01/01/2024"));
        assertFalse(predicate.test(new PersonBuilder().withAvailabilities("12/12/2024").build()));

        // Multiple dates
        predicate = new AvailableAtDatePredicate(List.of("01/01/2024", "12/12/2024"));
        assertFalse(predicate.test(new PersonBuilder().withAvailabilities("02/01/2024").build()));

        // Only one matching date
        predicate = new AvailableAtDatePredicate(List.of("01/01/2024"));
        assertFalse(predicate.test(new PersonBuilder().withAvailabilities("12/12/2024", "03/01/2024").build()));
    }

    @Test
    public void toStringMethod() throws Exception {
        AvailableAtDatePredicate predicate = new AvailableAtDatePredicate(List.of("01/01/2024"));
        String expected = AvailableAtDatePredicate.class.getCanonicalName()
            + "{keywords=" + List.of("01/01/2024") + "}";
        assertTrue(predicate.toString().equals(expected));
    }
}
