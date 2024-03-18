package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagsContainKeywordsPredicateTest{

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagsContainKeywordsPredicate firstPredicate = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        TagsContainKeywordsPredicate secondPredicate = new TagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainKeywordsPredicate firstPredicateCopy = new TagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Collections.singletonList("School"));
        assertTrue(predicate.test(new PersonBuilder().withTags("School", "Work").build()));

        // Multiple keywords
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("School", "Work"));
        assertTrue(predicate.test(new PersonBuilder().withTags("School", "Work").build()));

        // Mixed-case keywords
        predicate = new TagsContainKeywordsPredicate(List.of("School"));
        assertTrue(predicate.test(new PersonBuilder().withTags("sChooL").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("School").build()));

        // Only one matching keyword
        predicate = new TagsContainKeywordsPredicate(Arrays.asList("School", "Work"));
        assertFalse(predicate.test(new PersonBuilder().withTags("School").build()));

        // Non-matching keyword
        predicate = new TagsContainKeywordsPredicate(List.of("Work"));
        assertFalse(predicate.test(new PersonBuilder().withName("School").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(keywords);

        String expected = TagsContainKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

