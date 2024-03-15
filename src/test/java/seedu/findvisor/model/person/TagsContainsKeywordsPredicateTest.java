package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.findvisor.model.tag.TagsContainsKeywordsPredicate;
import seedu.findvisor.testutil.PersonBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywords = Arrays.asList(new String[]{"tag1"});
        List<String> secondPredicateKeywords = Arrays.asList(new String[]{"tag2", "tag3"});

        TagsContainsKeywordsPredicate firstPredicate = new TagsContainsKeywordsPredicate(firstPredicateKeywords);
        TagsContainsKeywordsPredicate secondPredicate = new TagsContainsKeywordsPredicate(secondPredicateKeywords);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(firstPredicateKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags-> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // single tag
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(
                Arrays.asList(new String[]{"friends"}));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // keyword is a substring
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList(new String[]{"pru"}));
        assertTrue(predicate.test(new PersonBuilder().withTags("PRUActive", "PRUEssential").build()));

        // multiple tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList(new String[]{"friends", "husband"}));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends", "husband", "wife").build()));
    }

    @Test
    public void test_tagsDoesNotContainsKeywords_returnsFalse() {
        // Non-matching keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(
                Arrays.asList(new String[]{"basketball", "football"}));
        assertFalse(predicate.test(new PersonBuilder().withTags("friends", "husband", "wife").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = Arrays.asList(new String[]{"exampleTag1, exampleTag2"});
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(keywords);
        String expected = TagsContainsKeywordsPredicate.class.getCanonicalName()
                + "{tags=" + keywords.toString() + "}";
        assertEquals(expected, predicate.toString());
    }
}
