package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IsSamePersonPredicateTest {

    @Test
    public void equals() {
        Person firstPredicateSelectedPerson = new PersonBuilder().build();
        Person secondPredicateSelectedPerson = new PersonBuilder().build();

        IsSamePersonPredicate firstPredicate = new IsSamePersonPredicate(firstPredicateSelectedPerson);
        IsSamePersonPredicate secondPredicate = new IsSamePersonPredicate(secondPredicateSelectedPerson);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsSamePersonPredicate firstPredicateCopy = new IsSamePersonPredicate(firstPredicateSelectedPerson);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isSamePerson_returnsTrue() {
        Person selectedPerson = new PersonBuilder().build();

        IsSamePersonPredicate predicate = new IsSamePersonPredicate(selectedPerson);
        assertTrue(predicate.test(selectedPerson));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        Person selectedPerson = new PersonBuilder().withName("Alice").build();
        Person nonSelectedPersonWithSameName = new PersonBuilder().withName("Alice").build();
        Person nonSelectedPersonWithDifferentName = new PersonBuilder().withName("Bob").build();

        // Different name
        IsSamePersonPredicate predicate = new IsSamePersonPredicate(selectedPerson);
        assertFalse(predicate.test(nonSelectedPersonWithDifferentName));

        // Same name but different Person object
        assertFalse(predicate.test(nonSelectedPersonWithSameName));
    }

    @Test
    public void toStringMethod() {
        Person selectedPerson = new PersonBuilder().build();
        IsSamePersonPredicate predicate = new IsSamePersonPredicate(selectedPerson);

        String expected = IsSamePersonPredicate.class.getCanonicalName() + "{selected person=" + selectedPerson + "}";
        assertEquals(expected, predicate.toString());
    }
}
