package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class DoctorNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DoctorNameContainsKeywordsPredicate firstPredicate =
                new DoctorNameContainsKeywordsPredicate(firstPredicateKeywordList);
        DoctorNameContainsKeywordsPredicate secondPredicate =
                new DoctorNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DoctorNameContainsKeywordsPredicate firstPredicateCopy =
                new DoctorNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_predicateReturnsDoctorsOnly() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Alice");
        DoctorNameContainsKeywordsPredicate firstPredicate =
                new DoctorNameContainsKeywordsPredicate(firstPredicateKeywordList);

        // Object type Doctor -> returns true
        Doctor p = new Doctor(new Nric("T1234567A"), new Name("Alice"), new DoB("2001-01-01"), new Phone("98765432"));
        assertTrue(firstPredicate.test(p));

        Patient d = new Patient(new Nric("T1234567A"), new Name("Alice"), new DoB("2001-01-01"), new Phone("98765432"));
        // Object type Doctor -> returns false
        assertFalse(firstPredicate.test(d));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        DoctorNameContainsKeywordsPredicate predicate =
                new DoctorNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DoctorNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DoctorNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DoctorNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DoctorNameContainsKeywordsPredicate predicate =
                new DoctorNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new DoctorNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // // Keywords match phone, email and address, but does not match name
        // predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        // assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
        // .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DoctorNameContainsKeywordsPredicate predicate = new DoctorNameContainsKeywordsPredicate(keywords);

        String expected = DoctorNameContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}