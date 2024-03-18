package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy =
                new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
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
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(Collections.singletonList("Finance"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Finance").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Quantitative", "Finance"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Quantitative Finance").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Finance", "Marketing"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Quantitative Finance").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("finAnce", "quaNtitative"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Quantitative Finance").build()));

        // Keywords without spacing
        predicate = new PersonContainsKeywordsPredicate(Collections.singletonList("quantitativefinance"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Quantitative Finance").build()));

        // Keywords with substrings
        predicate = new PersonContainsKeywordsPredicate(Collections.singletonList("nan"));
        assertTrue(predicate.test(new PersonBuilder().withDepartment("Quantitative Finance").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Finance").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Marketing"));
        assertFalse(predicate.test(new PersonBuilder().withDepartment("Finance").build()));

        // Keywords match phone, email and address, but does not match department
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withDepartment("Finance").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);

        String expected = PersonContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
