package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class HasUpcomingPredicateTest {

    @Test
    public void test_hasUpcoming_returnsTrue() {
        // Create a person with upcoming events
        Person personWithUpcoming = new PersonBuilder().withUpcoming("23-08-2024 1600").build();

        // Create the predicate
        HasUpcomingPredicate predicate = new HasUpcomingPredicate();

        // Test the predicate
        assertTrue(predicate.test(personWithUpcoming));
    }

    @Test
    public void test_noUpcoming_returnsFalse() {
        // Create a person without upcoming events
        Person personWithoutUpcoming = new PersonBuilder().withUpcoming("").build();

        // Create the predicate
        HasUpcomingPredicate predicate = new HasUpcomingPredicate();

        // Test the predicate
        assertFalse(predicate.test(personWithoutUpcoming));
    }
}
