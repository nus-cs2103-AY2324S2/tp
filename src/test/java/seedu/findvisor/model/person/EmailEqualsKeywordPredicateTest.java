package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.findvisor.testutil.PersonBuilder;

public class EmailEqualsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "example1@example.com";
        String secondPredicateKeyword = "example2@example.com";

        EmailEqualsKeywordPredicate firstPredicate = new EmailEqualsKeywordPredicate(firstPredicateKeyword);
        EmailEqualsKeywordPredicate secondPredicate = new EmailEqualsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailEqualsKeywordPredicate firstPredicateCopy = new EmailEqualsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different email -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailEqualsKeyword_returnsTrue() {
        EmailEqualsKeywordPredicate predicate = new EmailEqualsKeywordPredicate("example@example.com");
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Mixed-case keyword
        predicate = new EmailEqualsKeywordPredicate("EXAmple@example.COM");
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));
    }

    @Test
    public void test_emailDoesNotEqualKeywords_returnsFalse() {
        // Non-matching keyword
        EmailEqualsKeywordPredicate predicate = new EmailEqualsKeywordPredicate("example@example.com");
        assertFalse(predicate.test(new PersonBuilder().withEmail("123@example.com").build()));

        // Substring keyword
        predicate = new EmailEqualsKeywordPredicate("example");
        assertFalse(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Keywords match phone, but does not match email
        predicate = new EmailEqualsKeywordPredicate("91002921");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("91002921")
                .withEmail("alice@email.com").withAddress("MainStreet").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "example@example.com";
        EmailEqualsKeywordPredicate predicate = new EmailEqualsKeywordPredicate(keyword);

        String expected = EmailEqualsKeywordPredicate.class.getCanonicalName() + "{email=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
    
}
