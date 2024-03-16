package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyphrase = "first";
        String secondPredicateKeyphrase = "first second";

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeyphrase);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeyphrase);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeyphrase);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate("Alice Tan");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Not starting with matching keyword
        predicate = new NameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Annoying Alice").build()));

        // Still substring but not exact word
        predicate = new NameContainsKeywordsPredicate("lice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Not exact word and not starting with keyword
        predicate = new NameContainsKeywordsPredicate("lice");
        assertTrue(predicate.test(new PersonBuilder().withName("Annoying Alice").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate("aLIce bOB");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        Person alice = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();

        // Keywords match phone but does not match name
        predicate = new NameContainsKeywordsPredicate("12345");
        assertFalse(predicate.test(alice));

        // Keywords match email but does not match name
        predicate = new NameContainsKeywordsPredicate("alice@email.com");
        assertFalse(predicate.test(alice));

        // Keywords match address but does not match name
        predicate = new NameContainsKeywordsPredicate("Main");
        assertFalse(predicate.test(alice));
        predicate = new NameContainsKeywordsPredicate("Street");
        assertFalse(predicate.test(alice));

    }

    @Test
    public void toStringMethod() {
        String keyphrase = "keyphrase yapyap";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keyphrase);
        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keyphrase=" + keyphrase + "}";
        assertEquals(expected, predicate.toString());
    }
}
