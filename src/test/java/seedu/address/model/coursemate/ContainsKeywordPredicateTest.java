package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CourseMateBuilder;

public class ContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        ContainsKeywordPredicate firstPredicate = new ContainsKeywordPredicate(firstPredicateKeyword);
        ContainsKeywordPredicate secondPredicate = new ContainsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContainsKeywordPredicate firstPredicateCopy = new ContainsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different courseMate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containsKeywords_returnsTrue() {
        // One word
        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("Alice");
        assertTrue(predicate.test(new CourseMateBuilder().withName("Alice Bob").build()));

        // Multiple words
        predicate = new ContainsKeywordPredicate("Alice Bob");
        assertTrue(predicate.test(new CourseMateBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new ContainsKeywordPredicate("aLIce bOB");
        assertTrue(predicate.test(new CourseMateBuilder().withName("Alice Bob").build()));

        // Empty keyword
        predicate = new ContainsKeywordPredicate("");
        assertTrue(predicate.test(new CourseMateBuilder().withName("Alice").build()));

        // Keyword matches skills
        predicate = new ContainsKeywordPredicate("Java");
        assertTrue(predicate.test(new CourseMateBuilder().withSkills("Java").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Only partial keywords
        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("Bob Carol");
        assertFalse(predicate.test(new CourseMateBuilder().withName("Alice Carol").build()));

        // Non-matching keyword
        predicate = new ContainsKeywordPredicate("Carol");
        assertFalse(predicate.test(new CourseMateBuilder().withName("Alice Bob").build()));

        // A substring of a skill
        predicate = new ContainsKeywordPredicate("Jav");
        assertFalse(predicate.test(new CourseMateBuilder().withSkills("Java").build()));

        // Keywords match phone and email, but does not match name
        predicate = new ContainsKeywordPredicate("12345 alice@email.com");
        assertFalse(predicate.test(new CourseMateBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1 keyword2";
        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate(keyword);

        String expected = ContainsKeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
