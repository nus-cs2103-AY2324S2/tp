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

import seedu.address.model.cca.Cca;
import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CcaContainsKeywordPredicate firstPredicate = new CcaContainsKeywordPredicate(firstPredicateKeywordList);
        CcaContainsKeywordPredicate secondPredicate = new CcaContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CcaContainsKeywordPredicate firstPredicateCopy = new CcaContainsKeywordPredicate(firstPredicateKeywordList);
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
        CcaContainsKeywordPredicate predicate = new CcaContainsKeywordPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withCcas("friends").build()));

        // Multiple keywords
        predicate = new CcaContainsKeywordPredicate(Arrays.asList("friends", "classmates"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withCcas("friends").build()));

        // Only one matching keyword
        predicate = new CcaContainsKeywordPredicate(Arrays.asList("friends", "classmates"));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice Carol").withCcas("friends", "classmates").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CcaContainsKeywordPredicate predicate = new CcaContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new CcaContainsKeywordPredicate(Arrays.asList("Friends"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("Friend").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        CcaContainsKeywordPredicate predicate = new CcaContainsKeywordPredicate(keywords);

        String expected = CcaContainsKeywordPredicate.class.getCanonicalName() + "{ccas="
                + keywords.stream().map(Cca::new).collect(Collectors.toList()) + "}";
        assertEquals(expected, predicate.toString());
    }
}
