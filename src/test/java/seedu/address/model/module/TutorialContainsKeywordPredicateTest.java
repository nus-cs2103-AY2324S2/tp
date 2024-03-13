package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TutorialContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String predicateKeyword = "keyword";

        TutorialContainsKeywordPredicate predicate = new TutorialContainsKeywordPredicate(predicateKeyword);

        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // same values -> returns true
        TutorialContainsKeywordPredicate predicateCopy = new TutorialContainsKeywordPredicate(predicateKeyword);
        assertTrue(predicate.equals(predicateCopy));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

        // different person -> returns false
        String differentPredicateKeyword = "different keyword";
        TutorialContainsKeywordPredicate differentPredicate =
                new TutorialContainsKeywordPredicate(differentPredicateKeyword);
        assertFalse(predicate.equals(differentPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TutorialContainsKeywordPredicate predicate = new TutorialContainsKeywordPredicate("T01");
        assertTrue(predicate.test(new PersonBuilder().withTutorialClass("T01").build()));

        // Partial keyword
        predicate = new TutorialContainsKeywordPredicate("1");
        assertTrue(predicate.test(new PersonBuilder().withTutorialClass("T01").build()));

        // Mixed-case keywords
        predicate = new TutorialContainsKeywordPredicate("t01");
        assertTrue(predicate.test(new PersonBuilder().withTutorialClass("T01").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TutorialContainsKeywordPredicate predicate = new TutorialContainsKeywordPredicate("T15");
        assertFalse(predicate.test(new PersonBuilder().withTutorialClass("T01").build()));

        // Keyword matches name but does not match tutorial class
        predicate = new TutorialContainsKeywordPredicate("Alice");
        assertFalse(predicate.test(new PersonBuilder().withTutorialClass("T01").withName("Alice").build()));

        // Keyword matches email but does not match tutorial class
        predicate = new TutorialContainsKeywordPredicate("alice@gmail.com");
        assertFalse(predicate.test(new PersonBuilder().withTutorialClass("T01")
                .withEmail("alice@gmail.com").build()));

        // Keyword matches student id but does not match tutorial class
        predicate = new TutorialContainsKeywordPredicate("A1234567Z");
        assertFalse(predicate.test(new PersonBuilder().withTutorialClass("T01").withStudentId("A1234567Z").build()));

        // Keyword matches module code but does not match tutorial class
        predicate = new TutorialContainsKeywordPredicate("CS2101");
        assertFalse(predicate.test(new PersonBuilder().withTutorialClass("T01").withModuleCode("CS2101").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword";
        TutorialContainsKeywordPredicate predicate = new TutorialContainsKeywordPredicate(keyword);

        String expected = TutorialContainsKeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
