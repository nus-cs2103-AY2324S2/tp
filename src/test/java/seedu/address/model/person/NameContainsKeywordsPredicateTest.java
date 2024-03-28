package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
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
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));

        // Keywords match partial email
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("gmail"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@gmail.com").withAddress("Main Street").build()));

        // Keywords match partial phone number
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("3456"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Main Street").build()));

        // Keywords match partial address
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Kent"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd").build()));


        // Mixed-cased address
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("LOwer keNT RiDGe rd"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd").build()));

        // Keywords match tag
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Friends"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd")
                .withTags("Friends").build()));

        // Keywords match partial tag
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("fam"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd")
                .withTags("Friends", "family").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Non-matching phone number
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("87654321"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd")
                .withTags("Friends", "family").build()));

        // Non-matching email
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Bob@u.nus.edu"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd")
                .withTags("Friends", "family").build()));

        // Non-matching address
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Upper Thomson Road"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd")
                .withTags("Friends", "family").build()));

        // Non-matching tag
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Enemy"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345678")
                .withEmail("alice@gmail.com").withAddress("Lower Kent Ridge Rd")
                .withTags("Friends", "family").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);

        String expected = NameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
