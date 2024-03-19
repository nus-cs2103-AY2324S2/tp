package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonSearchPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = List.of("first", "second");

        PersonSearchPredicate firstPredicate = new PersonSearchPredicate(firstPredicateKeywordList);
        PersonSearchPredicate secondPredicate = new PersonSearchPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PersonSearchPredicate firstPredicateCopy = new PersonSearchPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PersonSearchPredicate predicate = new PersonSearchPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonSearchPredicate(List.of("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonSearchPredicate(List.of("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonSearchPredicate(List.of("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonSearchPredicate predicate = new PersonSearchPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new PersonSearchPredicate(List.of("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name, tags or assets
        predicate = new PersonSearchPredicate(List.of("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        PersonSearchPredicate predicate = new PersonSearchPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Multiple keywords
        predicate = new PersonSearchPredicate(List.of("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));

        // Mixed-case keywords
        predicate = new PersonSearchPredicate(List.of("fRieNdS", "cOllEaGuEs"));
        assertTrue(predicate.test(new PersonBuilder().withTags("colleagues").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonSearchPredicate predicate = new PersonSearchPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Non-matching keyword
        predicate = new PersonSearchPredicate(List.of("friends", "colleagues"));
        assertFalse(predicate.test(new PersonBuilder().withTags("family").build()));

        // Keywords match phone, email and address, but does not match name, tags or assets
        predicate = new PersonSearchPredicate(List.of("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("friends").build()));
    }

    @Test
    public void test_assetContainsKeywords_returnsTrue() {
        // One keyword
        PersonSearchPredicate predicate = new PersonSearchPredicate(Collections.singletonList("hammer"));
        assertTrue(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Multiple keywords
        predicate = new PersonSearchPredicate(List.of("hammer", "screwdriver"));
        assertTrue(predicate.test(new PersonBuilder().withAssets("screwdriver").build()));

        // Mixed-case keywords
        predicate = new PersonSearchPredicate(List.of("hAmMeR", "sCrEwDriVer"));
        assertTrue(predicate.test(new PersonBuilder().withAssets("screwdriver").build()));
    }

    @Test
    public void test_assetDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonSearchPredicate predicate = new PersonSearchPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Non-matching keyword
        predicate = new PersonSearchPredicate(List.of("helmet", "gloves"));
        assertFalse(predicate.test(new PersonBuilder().withAssets("hammer").build()));

        // Keywords match phone, email and address, but does not match name, tags or assets
        predicate = new PersonSearchPredicate(List.of("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice")
                                   .withPhone("12345")
                                   .withEmail("alice@email.com")
                                   .withAddress("Main Street")
                                   .withTags("friends")
                                   .withAssets("hammer")
                                   .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonSearchPredicate predicate = new PersonSearchPredicate(keywords);

        String expected = PersonSearchPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }

}
