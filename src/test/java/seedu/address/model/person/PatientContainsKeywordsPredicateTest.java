package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PatientContainsKeywordsPredicate firstPredicate =
                new PatientContainsKeywordsPredicate(firstPredicateKeywordList);
        PatientContainsKeywordsPredicate secondPredicate =
                new PatientContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PatientContainsKeywordsPredicate firstPredicateCopy =
                new PatientContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_predicateReturnsPatientsOnly() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Alice");
        PatientContainsKeywordsPredicate firstPredicate =
                new PatientContainsKeywordsPredicate(firstPredicateKeywordList);

        // Object type Patient -> returns true
        Patient p = new Patient(new Nric("T1234567A"), new Name("Alice"), new DoB("2001-01-01"), new Phone("98765432"));
        assertTrue(firstPredicate.test(p));

        Doctor d = new Doctor(new Nric("T1234567A"), new Name("Alice"), new DoB("2001-01-01"), new Phone("98765432"));
        // Object type Doctor -> returns false
        assertFalse(firstPredicate.test(d));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PatientContainsKeywordsPredicate predicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PatientContainsKeywordsPredicate predicate =
                new PatientContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PatientContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // // Keywords match phone, email and address, but does not match name
        // predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        // assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
        // .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PatientContainsKeywordsPredicate predicate = new PatientContainsKeywordsPredicate(keywords);

        String expected = PatientContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
