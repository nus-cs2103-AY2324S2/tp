package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.PersonBuilder;

public class ViewPredicateTest {

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Person firstPerson = new PersonBuilder().withName("Alice").build();
        ViewPredicate firstPredicate = new ViewPredicate(firstIndex, firstPerson);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ViewPredicate firstPredicateCopy = new ViewPredicate(firstIndex, firstPerson);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different index and person -> returns false
        Index secondIndex = Index.fromOneBased(2);
        Person secondPerson = new PersonBuilder().withName("Bob").build();
        ViewPredicate secondPredicate = new ViewPredicate(secondIndex, secondPerson);
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_samePerson_returnsTrue() {
        Index index = Index.fromOneBased(1);
        Person person = new PersonBuilder().withName("Alice").build();
        ViewPredicate predicate = new ViewPredicate(index, person);

        // Test with the exact same person object
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_differentPerson_returnsFalse() {
        Index index = Index.fromOneBased(1);
        Person person = new PersonBuilder().withName("Alice").build();
        ViewPredicate predicate = new ViewPredicate(index, person);

        // Test with a different person
        Person otherPerson = new PersonBuilder().withName("Bob").build();
        assertFalse(predicate.test(otherPerson));
    }
}
