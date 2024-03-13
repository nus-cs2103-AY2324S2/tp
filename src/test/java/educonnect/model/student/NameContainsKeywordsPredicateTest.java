package educonnect.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import educonnect.testutil.StudentBuilder;

public class NameContainsKeywordsPredicateTest {

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeyword);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("Alice");
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Incomplete name
        predicate = new NameContainsKeywordsPredicate("Alice B");
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Incomplete first name
        predicate = new NameContainsKeywordsPredicate("Ali");
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate("aLIce bOB");
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Mixed-case and incomplete name
        predicate = new NameContainsKeywordsPredicate("alIce bO");
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("x");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Name is contained in key word but key word is not contained in name
        predicate = new NameContainsKeywordsPredicate("Alice Carol Bob");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Same test but keywords are switched
        predicate = new NameContainsKeywordsPredicate("Alice Bob Carol");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords email but does not match name
        predicate = new NameContainsKeywordsPredicate("alice@email.com");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withStudentId("A1234567G")
                .withEmail("alice@email.com").withTelegramHandle("@balice").build()));

        // Keywords matches Student ID but does not match name
        predicate = new NameContainsKeywordsPredicate("A1234567G");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withStudentId("A1234567G")
                .withEmail("alice@email.com").withTelegramHandle("@balice").build()));

        // Keywords matches Telegram Handle but does not match name
        predicate = new NameContainsKeywordsPredicate("@balice");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withStudentId("A1234567G")
                .withEmail("alice@email.com").withTelegramHandle("@balice").build()));
    }

    @Test
    public void findStudent_emptyStringGiven_throwsIllegalArgumentException() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("");
        assertThrows(IllegalArgumentException.class, () -> predicate.test(
                new StudentBuilder().withName("Alice").build()));
    }

    @ Test
    public void findStudent_nullGiven_throwsNullPointerException() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(null);
        assertThrows(NullPointerException.class, () -> predicate.test(new StudentBuilder().withName("Alice").build()));
    }



    @Test
    public void toStringMethod() {
        String keywords = "keyword1 keyword2";
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
