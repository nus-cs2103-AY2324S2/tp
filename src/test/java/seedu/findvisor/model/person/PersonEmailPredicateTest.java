package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class PersonEmailPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "example1@example.com";
        String secondPredicateKeyword = "example2@example.com";

        PersonEmailPredicate firstPredicate = new PersonEmailPredicate(firstPredicateKeyword);
        PersonEmailPredicate secondPredicate = new PersonEmailPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonEmailPredicate firstPredicateCopy = new PersonEmailPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different email -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeyword_returnsTrue() {
        // Exact match
        PersonEmailPredicate predicate = new PersonEmailPredicate("example@example.com");
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Substring match
        predicate = new PersonEmailPredicate("example");
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Mixed-case keyword
        predicate = new PersonEmailPredicate("EXAmple@example.COM");
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainsKeyword_returnsFalse() {
        // Non-matching keyword
        PersonEmailPredicate predicate = new PersonEmailPredicate("example@example.com");
        assertFalse(predicate.test(new PersonBuilder().withEmail("123@example.com").build()));

        // Substring keyword
        predicate = new PersonEmailPredicate("com@example.com");
        assertFalse(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Keywords match phone, but does not match email
        predicate = new PersonEmailPredicate("91002921");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("91002921")
                .withEmail("alice@email.com").withAddress("MainStreet").build()));
    }

    @Test
    public void testGetPredicateDescription() {
        String keyword = "example@example.com";
        PersonEmailPredicate predicate = new PersonEmailPredicate(keyword);

        String expected = String.format("Email = \"%1$s\"", keyword);
        assertEquals(expected, predicate.getPredicateDescription());
    }

    @Test
    public void toStringMethod() {
        String keyword = "example@example.com";
        PersonEmailPredicate predicate = new PersonEmailPredicate(keyword);

        String expected = PersonEmailPredicate.class.getCanonicalName() + "{email=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
