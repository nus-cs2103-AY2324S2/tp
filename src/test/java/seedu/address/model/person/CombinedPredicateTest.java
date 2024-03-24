package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CombinedPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("friend");
        List<String> secondPredicateKeywordList = Arrays.asList("friend", "colleague");

        CombinedPredicate firstPredicate =
                new CombinedPredicate(new TagContainsKeywordsPredicate(firstPredicateKeywordList));
        CombinedPredicate secondPredicate =
                new CombinedPredicate(new TagContainsKeywordsPredicate(secondPredicateKeywordList));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CombinedPredicate firstPredicateCopy =
                new CombinedPredicate(new TagContainsKeywordsPredicate(firstPredicateKeywordList));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_combinedPredicateContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.emptyList());
        RelationshipContainsKeywordsPredicate relationshipPredicate =
                new RelationshipContainsKeywordsPredicate(Collections.emptyList());
        TagContainsKeywordsPredicate tagPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("friend"));
        CombinedPredicate combinedPredicate = new CombinedPredicate(namePredicate, relationshipPredicate, tagPredicate);
        assertTrue(combinedPredicate.test(new PersonBuilder().withTags("friend").build()));

        // Multiple keywords
        namePredicate = new NameContainsKeywordsPredicate(Collections.singletonList("Bob"));
        relationshipPredicate = new RelationshipContainsKeywordsPredicate(Collections.singletonList("client"));
        tagPredicate = new TagContainsKeywordsPredicate(Arrays.asList("friend", "colleague"));
        combinedPredicate = new CombinedPredicate(namePredicate, relationshipPredicate, tagPredicate);
        assertTrue(combinedPredicate.test(new PersonBuilder().withName("Bob").build()));
        assertTrue(combinedPredicate.test(new PersonBuilder().withRelationship("client").build()));
        assertTrue(combinedPredicate.test(new PersonBuilder().withTags("friend").build()));
        assertTrue(combinedPredicate.test(new PersonBuilder().withTags("colleague").build()));
    }

    @Test
    public void test_combinedPredicateDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        CombinedPredicate combinedPredicate = new CombinedPredicate(tagPredicate);
        assertFalse(combinedPredicate.test(new PersonBuilder().withTags("friend").build()));

        // Non-matching keyword
        tagPredicate = new TagContainsKeywordsPredicate(Arrays.asList("associate"));
        combinedPredicate = new CombinedPredicate(tagPredicate);
        assertFalse(combinedPredicate.test(new PersonBuilder().withTags("friend").build()));
        assertFalse(combinedPredicate.test(new PersonBuilder().withTags("colleague").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("friend", "colleague");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);
        CombinedPredicate combinedPredicate = new CombinedPredicate(predicate);

        String expected = CombinedPredicate.class.getCanonicalName()
                + "{predicateList="
                + TagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}"
                + "}";
        assertEquals(expected, combinedPredicate.toString());
    }
}
