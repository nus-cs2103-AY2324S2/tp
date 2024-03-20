package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.realodex.testutil.PersonBuilder;

public class NameContainsKeyphrasePredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyphrase = "first";
        String secondPredicateKeyphrase = "first second";

        NameContainsKeyphrasePredicate firstPredicate = new NameContainsKeyphrasePredicate(firstPredicateKeyphrase);
        NameContainsKeyphrasePredicate secondPredicate = new NameContainsKeyphrasePredicate(secondPredicateKeyphrase);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeyphrasePredicate firstPredicateCopy = new NameContainsKeyphrasePredicate(firstPredicateKeyphrase);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeyphrase_returnsTrue() {
        // Keyphrase is one word
        NameContainsKeyphrasePredicate predicate = new NameContainsKeyphrasePredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Keyphrase is more than one word
        predicate = new NameContainsKeyphrasePredicate("Alice Tan");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Tan").build()));

        // Name does not start with matching keyphrase
        predicate = new NameContainsKeyphrasePredicate("Alice");
        assertTrue(predicate.test(new PersonBuilder().withName("Annoying Alice").build()));

        // Keyphrase is not the full word
        predicate = new NameContainsKeyphrasePredicate("lice");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Not exact word and not starting with keyphrase
        predicate = new NameContainsKeyphrasePredicate("lice");
        assertTrue(predicate.test(new PersonBuilder().withName("Annoying Alice").build()));

        // Mixed-case keyphrase
        predicate = new NameContainsKeyphrasePredicate("aLIce bOB");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeyphrase_returnsFalse() {

        // Non-matching keyphrase
        NameContainsKeyphrasePredicate predicate = new NameContainsKeyphrasePredicate("Carol");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        Person alice = new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build();

        // Keyphrase match phone but does not match name
        predicate = new NameContainsKeyphrasePredicate("12345");
        assertFalse(predicate.test(alice));

        // Keyphrase match email but does not match name
        predicate = new NameContainsKeyphrasePredicate("alice@email.com");
        assertFalse(predicate.test(alice));

        // Keyphrase match address but does not match name
        predicate = new NameContainsKeyphrasePredicate("Main");
        assertFalse(predicate.test(alice));
        predicate = new NameContainsKeyphrasePredicate("Street");
        assertFalse(predicate.test(alice));

    }

    @Test
    public void toStringMethod() {
        String keyphrase = "keyphrase yapyap";
        NameContainsKeyphrasePredicate predicate = new NameContainsKeyphrasePredicate(keyphrase);
        String expected = NameContainsKeyphrasePredicate.class.getCanonicalName() + "{keyphrase=" + keyphrase + "}";
        assertEquals(expected, predicate.toString());
    }
}
