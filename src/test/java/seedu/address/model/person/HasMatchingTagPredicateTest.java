package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class HasMatchingTagPredicateTest {
    @Test
    public void testEquals() {
        List<String> firstPredicateTagList = Collections.singletonList("first");
        List<String> secondPredicateTagList = Arrays.asList("first", "second");

        HasMatchingTagPredicate firstPredicate = new HasMatchingTagPredicate(firstPredicateTagList);
        HasMatchingTagPredicate secondPredicate = new HasMatchingTagPredicate(secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        HasMatchingTagPredicate firstPredicateCopy = new HasMatchingTagPredicate(firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag list -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_hasMatchingTags_returnsTrue() {
        // One filter tag
        HasMatchingTagPredicate predicate = new HasMatchingTagPredicate(Collections.singletonList("supplier"));
        assertTrue(predicate.test(new PersonBuilder().withTags("supplier").build()));

        // Multiple filter tags
        predicate = new HasMatchingTagPredicate(Arrays.asList("supplier", "seafood"));
        assertFalse(predicate.test(new PersonBuilder().withTags("supplier").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("seafood", "supplier").build()));

        // Only one matching tag
        predicate = new HasMatchingTagPredicate(Arrays.asList("seafood"));
        assertTrue(predicate.test(new PersonBuilder().withTags("supplier", "seafood").build()));

        // Mixed-case filter tags
        predicate = new HasMatchingTagPredicate(Arrays.asList("Supplier", "SeaFood"));
        assertTrue(predicate.test(new PersonBuilder().withTags("supplier", "seafood").build()));
    }

    @Test
    public void test_noMatchingTags_returnsFalse() {
        // No matching tags
        HasMatchingTagPredicate predicate = new HasMatchingTagPredicate(Arrays.asList("employee"));
        assertFalse(predicate.test(new PersonBuilder().withTags("supplier").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("supplier", "seafood").build()));
        assertFalse(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void testToString() {
        List<String> tags = List.of("seafood", "supplier");
        HasMatchingTagPredicate predicate = new HasMatchingTagPredicate(tags);

        String expected = HasMatchingTagPredicate.class.getCanonicalName() + "{filter tags=" + tags + "}";
        assertEquals(expected, predicate.toString());
    }

}
