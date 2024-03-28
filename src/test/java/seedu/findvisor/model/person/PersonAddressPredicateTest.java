package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class PersonAddressPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "Block 312, Amy Street 1";
        String secondPredicateKeyword = "Block 123, Bobby Ave 3";

        PersonAddressPredicate firstPredicate = new PersonAddressPredicate(firstPredicateKeyword);
        PersonAddressPredicate secondPredicate = new PersonAddressPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonAddressPredicate firstPredicateCopy = new PersonAddressPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressEmpty_exceptionThrown() {
        // empty address -> exception thrown
        PersonAddressPredicate predicate = new PersonAddressPredicate(" ");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            predicate.test(new PersonBuilder().withAddress("Alice").build());
        });
        assertEquals("subString parameter cannot be empty", exception.getMessage());
    }

    @Test
    public void test_addressContainsKeyword_returnsTrue() {
        // different case
        PersonAddressPredicate predicate = new PersonAddressPredicate("30 charlie street");
        assertTrue(predicate.test(new PersonBuilder().withAddress("30 Charlie Street").build()));

        // Exact word
        predicate = new PersonAddressPredicate("12 Bun Ave");
        assertTrue(predicate.test(new PersonBuilder().withAddress("12 Bun Ave").build()));

        // address contains keyword
        predicate = new PersonAddressPredicate("Apple");
        assertTrue(predicate.test(new PersonBuilder().withAddress("30 Apple Street").build()));
    }

    @Test
    public void test_addressDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonAddressPredicate predicate = new PersonAddressPredicate("Apple");
        assertFalse(predicate.test(new PersonBuilder().withAddress("30 Charlie Street").build()));

        // Reversed keyword
        predicate = new PersonAddressPredicate("Street Charlie 30");
        assertFalse(predicate.test(new PersonBuilder().withAddress("30 Charlie Street").build()));

        // Keywords match phone, but does not match address
        predicate = new PersonAddressPredicate("SixthStreet");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("91002921")
                .withEmail("alice@email.com").withAddress("MainStreet").build()));
    }

    @Test
    public void testGetPredicateDescription() {
        String keyword = "30 Apple Ave";
        PersonAddressPredicate predicate = new PersonAddressPredicate(keyword);

        String expected = String.format("Address = \"%1$s\"", keyword);
        assertEquals(expected, predicate.getPredicateDescription());
    }

    @Test
    public void toStringMethod() {
        String keyword = "30 Apple Ave";
        PersonAddressPredicate predicate = new PersonAddressPredicate(keyword);

        String expected = PersonAddressPredicate.class.getCanonicalName() + "{address=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
