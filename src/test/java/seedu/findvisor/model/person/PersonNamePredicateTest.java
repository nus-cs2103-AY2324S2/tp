package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class PersonNamePredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        PersonNamePredicate firstPredicate = new PersonNamePredicate(firstPredicateKeyword);
        PersonNamePredicate secondPredicate = new PersonNamePredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonNamePredicate firstPredicateCopy = new PersonNamePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameEmpty_exceptionThrown() {
        // empty name -> exception thrown
        PersonNamePredicate predicate = new PersonNamePredicate(" ");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            predicate.test(new PersonBuilder().withName("Alice").build());
        });
        assertEquals("subString parameter cannot be empty", exception.getMessage());
    }

    @Test
    public void test_nameContainsKeyword_returnsTrue() {
        // One keyword
        PersonNamePredicate predicate = new PersonNamePredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Exact word
        predicate = new PersonNamePredicate("Alice Tan Li Li");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan Li Li").build()));

        // Name contains keyword
        predicate = new PersonNamePredicate("Ali");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Mixed-case keyword
        predicate = new PersonNamePredicate("aLIce");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Yeoh").build()));
    }

    @Test
    public void test_nameDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonNamePredicate predicate = new PersonNamePredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Reversed keyword
        predicate = new PersonNamePredicate("Tan Alex");
        assertFalse(predicate.test(new PersonBuilder().withName("Alex Tan").build()));

        // Keywords match phone, but does not match name
        predicate = new PersonNamePredicate("91002921");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("91002921")
                .withEmail("alice@email.com").withAddress("MainStreet").build()));
    }

    @Test
    public void testGetPredicateDescription() {
        String keyword = "Alice";
        PersonNamePredicate predicate = new PersonNamePredicate(keyword);

        String expected = String.format("Name = \"%1$s\"", keyword);
        assertEquals(expected, predicate.getPredicateDescription());
    }

    @Test
    public void toStringMethod() {
        String keyword = "Alice";
        PersonNamePredicate predicate = new PersonNamePredicate(keyword);

        String expected = PersonNamePredicate.class.getCanonicalName() + "{name=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
