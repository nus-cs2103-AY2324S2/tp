package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DoctorBuilder;

public class DoctorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DoctorContainsKeywordsPredicate firstPredicate =
                new DoctorContainsKeywordsPredicate(firstPredicateKeywordList);
        DoctorContainsKeywordsPredicate secondPredicate =
                new DoctorContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DoctorContainsKeywordsPredicate firstPredicateCopy =
                new DoctorContainsKeywordsPredicate(firstPredicateKeywordList);
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
        DoctorContainsKeywordsPredicate firstPredicate =
                new DoctorContainsKeywordsPredicate(firstPredicateKeywordList);

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
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new DoctorBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nricContainsKeywords_returnsTrue() {
        // One NRIC
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.singletonList("T1234567A"));
        assertTrue(predicate.test(new DoctorBuilder().withNric("T1234567A").build()));

        // NRIC substring
        predicate = new DoctorContainsKeywordsPredicate(Collections.singletonList("1234"));
        assertTrue(predicate.test(new DoctorBuilder().withNric("T1234567A").build()));

        // Multiple NRICs
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("T0170911E", "S1234567A"));
        assertTrue(predicate.test(new DoctorBuilder().withNric("T0170911E").build()));
    }

    @Test
    public void test_nricContainsKeywords_returnsFalse() {
        // Zero keywords
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DoctorBuilder().withNric("T0170911E").build()));

        // Non-matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("T0170911E"));
        assertFalse(predicate.test(new DoctorBuilder().withNric("S1234567A").build()));
    }

    @Test
    public void test_dobContainsKeywords_returnsTrue() {
        // One dob
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.singletonList("2001-01-01"));
        assertTrue(predicate.test(new DoctorBuilder().withDoB("2001-01-01").build()));

        // dob substring
        predicate = new DoctorContainsKeywordsPredicate(Collections.singletonList("01"));
        assertTrue(predicate.test(new DoctorBuilder().withDoB("2001-01-01").build()));

        // multiple dob
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("2001-01-01", "2001-01-02"));
        assertTrue(predicate.test(new DoctorBuilder().withDoB("2001-01-01").build()));
    }

    @Test
    public void test_dobContainsKeywords_returnsFalse() {
        // Zero keywords
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DoctorBuilder().withDoB("2001-01-01").build()));

        // Non-matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("22"));
        assertFalse(predicate.test(new DoctorBuilder().withDoB("2001-01-01").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One phone
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.singletonList("91234567"));
        assertTrue(predicate.test(new DoctorBuilder().withPhone("91234567").build()));

        // phone substring
        predicate = new DoctorContainsKeywordsPredicate(Collections.singletonList("4567"));
        assertTrue(predicate.test(new DoctorBuilder().withPhone("91234567").build()));

        // multiple phone numbers
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("91234567", "99991111"));
        assertTrue(predicate.test(new DoctorBuilder().withPhone("91234567").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsFalse() {
        // Zero keywords
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new DoctorBuilder().withPhone("91234567").build()));

        // Non-matching keyword
        predicate = new DoctorContainsKeywordsPredicate(Arrays.asList("7654"));
        assertFalse(predicate.test(new DoctorBuilder().withPhone("91234567").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DoctorContainsKeywordsPredicate predicate = new DoctorContainsKeywordsPredicate(keywords);

        String expected = DoctorContainsKeywordsPredicate.class.getCanonicalName()
                + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
