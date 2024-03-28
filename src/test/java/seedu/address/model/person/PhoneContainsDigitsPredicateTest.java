package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class PhoneContainsDigitsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("94351253");
        List<String> secondPredicateKeywordList = Arrays.asList("94351253", "98765432");

        PhoneContainsDigitsPredicate firstPredicate = new PhoneContainsDigitsPredicate(firstPredicateKeywordList);
        PhoneContainsDigitsPredicate secondPredicate = new PhoneContainsDigitsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PhoneContainsDigitsPredicate firstPredicateCopy = new PhoneContainsDigitsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different instance -> returns false
        assertFalse(firstPredicate.equals(new ArrayList<>()));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_numberContainsDigits_returnsTrue() {
        // One keyword
        PhoneContainsDigitsPredicate predicate =
                new PhoneContainsDigitsPredicate(Collections.singletonList("94351253"));
        assertTrue(predicate.test(new ClientBuilder().withPhone("94351253").build()));
    }

    @Test
    public void test_phoneDoesNotContainDigits_returnsFalse() {
        // Zero keywords
        PhoneContainsDigitsPredicate predicate = new PhoneContainsDigitsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withPhone("94351253").build()));

        // Non-matching keyword
        predicate = new PhoneContainsDigitsPredicate(List.of("95352563"));
        assertFalse(predicate.test(new ClientBuilder().withPhone("98765432").build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new PhoneContainsDigitsPredicate(Arrays.asList("Alice", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> phones = List.of("94351253", "98765432");
        PhoneContainsDigitsPredicate predicate = new PhoneContainsDigitsPredicate(phones);

        String expected = PhoneContainsDigitsPredicate.class.getCanonicalName() + "{phones=" + phones + "}";
        assertEquals(expected, predicate.toString());
    }
}
