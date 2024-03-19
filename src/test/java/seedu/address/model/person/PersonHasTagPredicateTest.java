package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TestUtil;

public class PersonHasTagPredicateTest {
    @Test
    public void equals() throws Exception {
        List<Tag> firstPredicateKeywordList = TestUtil.stringsToTags(Collections.singletonList("first"));
        List<Tag> secondPredicateKeywordList = TestUtil.stringsToTags(Arrays.asList("first", "second"));

        PersonHasTagPredicate firstPredicate = new PersonHasTagPredicate(firstPredicateKeywordList);
        PersonHasTagPredicate secondPredicate = new PersonHasTagPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagPredicate firstPredicateCopy = new PersonHasTagPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() throws Exception {
        // One keyword
        PersonHasTagPredicate predicate =
                new PersonHasTagPredicate(TestUtil.stringsToTags(Collections.singletonList("friend")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend").build()));

        // Multiple keywords
        predicate = new PersonHasTagPredicate(TestUtil.stringsToTags(Arrays.asList("friend", "TA")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "TA").build()));

        // Only one matching keyword
        predicate = new PersonHasTagPredicate(TestUtil.stringsToTags(Arrays.asList("friend", "CCA")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "TA").build()));

        // Mixed-case keywords, not implemented yet
        // add code test here
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() throws Exception {
        // Zero keywords
        PersonHasTagPredicate predicate = new PersonHasTagPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // Non-matching keyword
        predicate = new PersonHasTagPredicate(TestUtil.stringsToTags(Arrays.asList("TA")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "CCA").build()));

        // Keywords match phone, address, but does not match tags
        predicate = new PersonHasTagPredicate(TestUtil.stringsToTags(
                Arrays.asList("12345", "Main", "Street")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").withPhone("12345")
                .withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() throws Exception {
        List<Tag> keywords = TestUtil.stringsToTags(List.of("keyword1", "keyword2"));
        PersonHasTagPredicate predicate = new PersonHasTagPredicate(keywords);

        String expected = PersonHasTagPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
