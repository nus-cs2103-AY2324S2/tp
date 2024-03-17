package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MatchingEmailPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("alice@gmail.com");
        List<String> secondPredicateKeywordList = Arrays.asList("bob@gmail.com", "carl@yahoo.com");

        MatchingEmailPredicate firstPredicate =
                new MatchingEmailPredicate(firstPredicateKeywordList);
        MatchingEmailPredicate secondPredicate =
                new MatchingEmailPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatchingEmailPredicate firstPredicateCopy =
                new MatchingEmailPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matchingEmail_returnsTrue() {
        // One number
        MatchingEmailPredicate predicate =
                new MatchingEmailPredicate(Collections.singletonList("alice@gmail.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));
    }

    @Test
    public void test_notMatchingEmail_returnsFalse() {
        // Zero
        MatchingEmailPredicate predicate = new MatchingEmailPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));

        // Non-matching keyword
        predicate = new MatchingEmailPredicate(Arrays.asList("alice@gmail.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("bob@gmail.com").build()));

        //  match address, phone and name, but does not match email
        predicate = new MatchingEmailPredicate(Arrays.asList("12345", "alice@email.com", "Alice", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@gmail.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> emails = List.of("email1", "email2");
        MatchingEmailPredicate predicate = new MatchingEmailPredicate(emails);

        String expected = MatchingEmailPredicate.class.getCanonicalName() + "{keywords=" + emails + "}";
        assertEquals(expected, predicate.toString());
    }
}
