package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RelationshipContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Client");
        List<String> secondPredicateKeywordList = Arrays.asList("Client", "Partner");

        RelationshipContainsKeywordsPredicate firstPredicate =
                new RelationshipContainsKeywordsPredicate(firstPredicateKeywordList);
        RelationshipContainsKeywordsPredicate secondPredicate =
                new RelationshipContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RelationshipContainsKeywordsPredicate firstPredicateCopy =
                new RelationshipContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_relationshipContainsKeywords_returnsTrue() {
        // One keyword
        RelationshipContainsKeywordsPredicate predicate =
                new RelationshipContainsKeywordsPredicate(Collections.singletonList("Client"));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("Client").build()));

        // Multiple keywords
        predicate = new RelationshipContainsKeywordsPredicate(Arrays.asList("Client", "Partner"));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("Client").build()));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("Partner").build()));

        // Mixed-case keywords
        predicate = new RelationshipContainsKeywordsPredicate(Arrays.asList("cLiEnt", "pArTnEr"));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("Client").build()));
        assertTrue(predicate.test(new PersonBuilder().withRelationship("Partner").build()));
    }

    @Test
    public void test_relationshipDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RelationshipContainsKeywordsPredicate predicate =
                new RelationshipContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRelationship("Client").build()));

        // Non-matching keyword
        predicate = new RelationshipContainsKeywordsPredicate(Arrays.asList("Associate"));
        assertFalse(predicate.test(new PersonBuilder().withRelationship("Client").build()));
        assertFalse(predicate.test(new PersonBuilder().withRelationship("Partner").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("Client", "Partner");
        RelationshipContainsKeywordsPredicate predicate = new RelationshipContainsKeywordsPredicate(keywords);

        String expected =
                RelationshipContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }


}
