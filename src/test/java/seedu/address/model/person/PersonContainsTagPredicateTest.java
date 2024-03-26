package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsTagPredicateTest {

    @Test
    public void equals() {
        Tag firstPredicateTag = new Tag("family");
        Tag secondPredicateTag = new Tag("home");

        PersonContainsTagPredicate firstPredicate = new PersonContainsTagPredicate(firstPredicateTag);
        PersonContainsTagPredicate secondPredicate = new PersonContainsTagPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsTagPredicate firstPredicateCopy = new PersonContainsTagPredicate(firstPredicateTag);
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
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(new Tag("family"));
        assertTrue(predicate.test(new PersonBuilder().withTags("family").build()));

        // Upper case
        assertTrue(predicate.test(new PersonBuilder().withTags("Family").build()));

        // Mixed case
        assertTrue(predicate.test(new PersonBuilder().withTags("fAMilY").build()));
    }

}
