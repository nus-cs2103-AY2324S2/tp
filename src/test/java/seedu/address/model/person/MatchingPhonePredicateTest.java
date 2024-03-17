package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MatchingPhonePredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("12345");
        List<String> secondPredicateKeywordList = Arrays.asList("1234", "56789");

        MatchingPhonePredicate firstPredicate =
                new MatchingPhonePredicate(firstPredicateKeywordList);
        MatchingPhonePredicate secondPredicate =
                new MatchingPhonePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatchingPhonePredicate firstPredicateCopy =
                new MatchingPhonePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matchingPhone_returnsTrue() {
        // One number
        MatchingPhonePredicate predicate =
                new MatchingPhonePredicate(Collections.singletonList("12345"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("12345").build()));
    }

    @Test
    public void test_notMatchingPhone_returnsFalse() {
        // Zero
        MatchingPhonePredicate predicate = new MatchingPhonePredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("12345").build()));

        // Non-matching keyword
        predicate = new MatchingPhonePredicate(Arrays.asList("12345"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("56789").build()));

        //  match address, email and name, but does not match phone
        predicate = new MatchingPhonePredicate(Arrays.asList("12345", "alice@email.com", "Alice", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("59999")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> numbers = List.of("number1", "number2");
        MatchingPhonePredicate predicate = new MatchingPhonePredicate(numbers);

        String expected = MatchingPhonePredicate.class.getCanonicalName() + "{keywords=" + numbers + "}";
        assertEquals(expected, predicate.toString());
    }
}
