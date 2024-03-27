package seedu.internhub.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.internhub.testutil.PersonBuilder;

public class MatchingTagPredicateTest {


    @Test
    public void equals() {
        String firstTagPredicate = "NR";
        String secondTagPredicate = "I";

        MatchingTagPredicate firstPredicate = new MatchingTagPredicate(firstTagPredicate);
        MatchingTagPredicate secondPredicate = new MatchingTagPredicate(secondTagPredicate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatchingTagPredicate firstPredicateCopy = new MatchingTagPredicate(firstTagPredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matchingTags_returnsTrue() {
        MatchingTagPredicate predicate = new MatchingTagPredicate("I");
        assertTrue(predicate.test(new PersonBuilder().withTags("I").build()));
    }

    @Test
    public void test_nonMatchingTags_returnsFalse() {
        // Zero keywords
        MatchingTagPredicate predicate = new MatchingTagPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withTags("I").build()));

        // Non-matching keyword
        predicate = new MatchingTagPredicate("O");
        assertFalse(predicate.test(new PersonBuilder().withTags("I").build()));

        // Keywords match phone, email and address, but does not match name
        assertFalse(predicate.test(new PersonBuilder().withName("NR").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    /*
    @Test
    public void toStringMethod() {
        String tag = "NR";
        MatchingTagPredicate predicate = new MatchingTagPredicate(tag);

        String expected = MatchingTagPredicate.class.getCanonicalName() + "{keywords=" + tag + "}";
        assertEquals(expected, predicate.toString());
    }
    */

}
