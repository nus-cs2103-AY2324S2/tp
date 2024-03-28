package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GroupContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("TUT01");
        List<String> secondPredicateKeywordList = Arrays.asList("TUT01", "TUT02");

        GroupContainsKeywordsPredicate firstPredicate = new GroupContainsKeywordsPredicate(firstPredicateKeywordList);
        GroupContainsKeywordsPredicate secondPredicate = new GroupContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GroupContainsKeywordsPredicate firstPredicateCopy =
                new GroupContainsKeywordsPredicate(firstPredicateKeywordList);

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
        GroupContainsKeywordsPredicate predicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList("TUT10"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withGroups("TUT10").build()));

        // Multiple keywords
        predicate = new GroupContainsKeywordsPredicate(Arrays.asList("TUT10", "LAB05"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withGroups("TUT10").build()));

        // Only one matching keyword
        predicate = new GroupContainsKeywordsPredicate(Arrays.asList("TUT10", "LAB05"));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice Carol").withGroups("TUT10", "LAB05").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new GroupContainsKeywordsPredicate(Arrays.asList("TUT10"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withGroups("TUT11").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("TUT01", "TUT02");
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(keywords);

        String expected = GroupContainsKeywordsPredicate.class.getCanonicalName() + "{groups="
                + keywords.stream().map(Group::new).collect(Collectors.toList()) + "}";
        assertEquals(expected, predicate.toString());
    }
}
