package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class TagContainsKeywordsPredicateTest {
    @Test
    public void equalsTest() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = List.of("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> equals
        assertEquals(firstPredicate, firstPredicate);

        // same values -> equals
        TagContainsKeywordsPredicate firstPredicateDuplicate = new TagContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateDuplicate);

        // null -> not equals
        assertNotEquals(null, firstPredicate);

        // different predicate -> not equals
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PatientBuilder().withTags("depression").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(List.of("diabetes"));
        assertFalse(predicate.test(new PatientBuilder().withTags("depression").build()));

        // Keywords match patient hospital id, name, preferred name, food preference, family condition and hobby,
        // but does not match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("12344", "Caroline", "Li", "Carol", "Pasta",
                "Daughter", "not", "in", "Singapore", "swimming", "diabetes"));
        assertFalse(predicate.test(new PatientBuilder().withPatientHospitalId("12344").withName("Caroline Li")
                .withPreferredName("Carol").withFoodPreference("Pasta").withFamilyCondition("Daughter not in Singapore")
                .withHobby("swimming").withTags("depression").build()));
    }

    @Test
    public void test_tagDoesContainKeywords_returnsTrue() {
        TagContainsKeywordsPredicate predicate;

        // Matching keyword
        predicate = new TagContainsKeywordsPredicate(List.of("diabetes"));
        assertTrue(predicate.test(new PatientBuilder().withTags("diabetes").build()));

        // Keywords does not match patient hospital id, name, preferred name, food preference,
        // family condition and hobby but only match tag
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("12345", "Carolina", "Lee", "Caroli", "Paste",
                "Son", "at", "China", "dancing", "diabetes"));
        assertTrue(predicate.test(new PatientBuilder().withPatientHospitalId("12344").withName("Caroline Li")
                .withPreferredName("Carol").withFoodPreference("Pasta").withFamilyCondition("Daughter not in Singapore")
                .withHobby("swimming").withTags("diabetes").build()));
    }

    @Test
    public void toStringTest() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(keywords);

        String expected = TagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
