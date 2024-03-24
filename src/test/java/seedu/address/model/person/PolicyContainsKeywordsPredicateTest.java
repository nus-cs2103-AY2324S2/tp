package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PolicyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("life insurance");
        List<String> secondPredicateKeywordList = Arrays.asList("life insurance", "car insurance");

        PolicyContainsKeywordsPredicate firstPredicate = new PolicyContainsKeywordsPredicate(firstPredicateKeywordList);
        PolicyContainsKeywordsPredicate secondPredicate = new PolicyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PolicyContainsKeywordsPredicate firstPredicateCopy =
                new PolicyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyContainsKeywords_returnsTrue() {
        // One keyword
        PolicyContainsKeywordsPredicate predicate =
                new PolicyContainsKeywordsPredicate(Collections.singletonList("life insurance"));
        assertTrue(predicate.test(new PersonBuilder().withPolicy("life insurance").build()));

        // Multiple keywords
        predicate = new PolicyContainsKeywordsPredicate(Arrays.asList("life insurance", "car insurance"));
        assertTrue(predicate.test(new PersonBuilder().withTags("life insurance").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("car insurance").build()));

        // Mixed-case keywords
        predicate = new PolicyContainsKeywordsPredicate(Arrays.asList("liFe InsUrAnCE", "cAR INsuRAnCE"));
        assertTrue(predicate.test(new PersonBuilder().withTags("life insurance").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("car insurance").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PolicyContainsKeywordsPredicate predicate = new PolicyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("life insurance").build()));

        // Non-matching keyword
        predicate = new PolicyContainsKeywordsPredicate(Arrays.asList("Associate"));
        assertFalse(predicate.test(new PersonBuilder().withTags("life insurance").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("car insurance").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("life insurance", "car insurance");
        PolicyContainsKeywordsPredicate predicate = new PolicyContainsKeywordsPredicate(keywords);

        String expected = TagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}

