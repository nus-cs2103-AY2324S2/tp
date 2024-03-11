package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameAndTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateNameKeywordList = Collections.singletonList("first");
        List<String> secondPredicateNameKeywordList = Arrays.asList("first", "second");
        List<String> firstPredicateTagKeywordList = Collections.singletonList("tag1");
        List<String> secondPredicateTagKeywordList = Arrays.asList("tag2", "tag3");

        NameAndTagContainsKeywordsPredicate firstPredicate =
                new NameAndTagContainsKeywordsPredicate(firstPredicateNameKeywordList, firstPredicateTagKeywordList);
        NameAndTagContainsKeywordsPredicate secondPredicate =
                new NameAndTagContainsKeywordsPredicate(secondPredicateNameKeywordList, secondPredicateTagKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameAndTagContainsKeywordsPredicate firstPredicateCopy =
                new NameAndTagContainsKeywordsPredicate(firstPredicateNameKeywordList, firstPredicateTagKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameAndTagContainsKeywords_returnsTrue() {
        // Both name and tag match
        NameAndTagContainsKeywordsPredicate predicate = new NameAndTagContainsKeywordsPredicate(Arrays.asList("Alice"),
                Arrays.asList("friend"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend").build()));

        // Only name matches
        predicate = new NameAndTagContainsKeywordsPredicate(Arrays.asList("Alice"), Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Only tag matches
        predicate = new NameAndTagContainsKeywordsPredicate(Collections.emptyList(), Arrays.asList("friend"));
        assertTrue(predicate.test(new PersonBuilder().withName("Bob").withTags("friend").build()));
    }

    @Test
    public void test_nameAndTagDoesNotContainKeywords_returnsFalse() {
        // Neither name nor tag match
        NameAndTagContainsKeywordsPredicate predicate = new NameAndTagContainsKeywordsPredicate(
                Arrays.asList("Nonexistent"), Arrays.asList("nonexistentTag"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withTags("friend").build()));
    }

    @Test
    public void test_nameContainsKeywordsButTagDoesNot_returnsFalse() {
        // Name matches, tag does not match
        NameAndTagContainsKeywordsPredicate predicate = new NameAndTagContainsKeywordsPredicate(Arrays.asList("Alice"),
                Arrays.asList("nonexistentTag"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend").build()));
    }

    @Test
    public void test_tagContainsKeywordsButNameDoesNot_returnsFalse() {
        // Tag matches, name does not match
        NameAndTagContainsKeywordsPredicate predicate = new NameAndTagContainsKeywordsPredicate(
                Arrays.asList("Nonexistent"), Arrays.asList("friend"));
        assertFalse(predicate.test(new PersonBuilder().withName("Bob").withTags("friend").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> nameKeywords = List.of("nameKeyword");
        List<String> tagKeywords = List.of("tagKeyword");
        NameAndTagContainsKeywordsPredicate predicate = new NameAndTagContainsKeywordsPredicate(nameKeywords,
                tagKeywords);

        String expected = "NameAndTagContainsKeywordsPredicate{"
                + "nameKeywords=" + nameKeywords
                + ", tagKeywords=" + tagKeywords
                + '}';
        assertEquals(expected, predicate.toString());
    }
}
