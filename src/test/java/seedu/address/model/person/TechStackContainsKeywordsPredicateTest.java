package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TechStackContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TechStackContainsKeywordsPredicate firstPredicate = new TechStackContainsKeywordsPredicate(firstPredicateKeywordList);
        TechStackContainsKeywordsPredicate secondPredicate = new TechStackContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TechStackContainsKeywordsPredicate firstPredicateCopy = new TechStackContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_techStackContainsKeywords_returnsTrue() {
        // One keyword
        TechStackContainsKeywordsPredicate predicate = new TechStackContainsKeywordsPredicate(Collections.singletonList("Java"));
        assertTrue(predicate.test(new PersonBuilder().withTechStack("Java Python").build()));

        // Multiple keywords
        predicate = new TechStackContainsKeywordsPredicate(Arrays.asList("Java", "Python"));
        assertTrue(predicate.test(new PersonBuilder().withTechStack("Java Python").build()));

        // Only one matching keyword
        predicate = new TechStackContainsKeywordsPredicate(Arrays.asList("Python", "C++"));
        assertTrue(predicate.test(new PersonBuilder().withTechStack("Java C++").build()));

        // Mixed-case keywords
        predicate = new TechStackContainsKeywordsPredicate(Arrays.asList("jAvA", "pyTHON"));
        assertTrue(predicate.test(new PersonBuilder().withTechStack("Java Python").build()));
    }

    @Test
    public void test_techStackDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TechStackContainsKeywordsPredicate predicate = new TechStackContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTechStack("Java").build()));

        // Non-matching keyword
        predicate = new TechStackContainsKeywordsPredicate(Arrays.asList("C++"));
        assertFalse(predicate.test(new PersonBuilder().withTechStack("Java Python").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new TechStackContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TechStackContainsKeywordsPredicate predicate = new TechStackContainsKeywordsPredicate(keywords);

        String expected = TechStackContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
