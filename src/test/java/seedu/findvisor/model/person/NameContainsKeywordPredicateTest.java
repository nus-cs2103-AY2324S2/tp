package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class NameContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        NameContainsKeywordPredicate firstPredicate = new NameContainsKeywordPredicate(firstPredicateKeyword);
        NameContainsKeywordPredicate secondPredicate = new NameContainsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordPredicate firstPredicateCopy = new NameContainsKeywordPredicate(firstPredicateKeyword);
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
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate(" ");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            predicate.test(new PersonBuilder().withName("Alice").build());
        });
        assertEquals("subString parameter cannot be empty", exception.getMessage());
    }

    @Test
    public void test_nameContainsKeyword_returnsTrue() {
        // One keyword
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Exact word
        predicate = new NameContainsKeywordPredicate("Alice Tan Li Li");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan Li Li").build()));

        // Name contains keyword
        predicate = new NameContainsKeywordPredicate("Ali");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Mixed-case keyword
        predicate = new NameContainsKeywordPredicate("aLIce");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Yeoh").build()));
    }

    @Test
    public void test_nameDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Reversed keyword
        predicate = new NameContainsKeywordPredicate("Tan Alex");
        assertFalse(predicate.test(new PersonBuilder().withName("Alex Tan").build()));

        // Keywords match phone, but does not match name
        predicate = new NameContainsKeywordPredicate("91002921");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("91002921")
                .withEmail("alice@email.com").withAddress("MainStreet").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "Alice";
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate(keyword);

        String expected = NameContainsKeywordPredicate.class.getCanonicalName() + "{name=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
