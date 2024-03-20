// This file is adapted from NameContainsKeywordsPredicateTest.java. Full credits to the original
// authors and CS2103T teaching team.
package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordPredicate firstPredicate = new TagContainsKeywordPredicate(firstPredicateKeywordList);
        TagContainsKeywordPredicate secondPredicate = new TagContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordPredicate firstPredicateCopy = new TagContainsKeywordPredicate(firstPredicateKeywordList);
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
        // One keyword
        TagContainsKeywordPredicate predicate = new TagContainsKeywordPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friends").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordPredicate(Arrays.asList("friends", "classmates"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friends").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordPredicate(Arrays.asList("friends", "classmates"));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice Carol").withTags("friends", "classmates").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordPredicate predicate = new TagContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordPredicate(Arrays.asList("Friends"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("Friend").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TagContainsKeywordPredicate predicate = new TagContainsKeywordPredicate(keywords);

        String expected = TagContainsKeywordPredicate.class.getCanonicalName() + "{tags="
                + keywords.stream().map(Tag::new).collect(Collectors.toList()) + "}";
        assertEquals(expected, predicate.toString());
    }
}
