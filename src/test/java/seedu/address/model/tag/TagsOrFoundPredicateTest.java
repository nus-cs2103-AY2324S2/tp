package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TagBuilder;


public class TagsOrFoundPredicateTest {

    @Test
    public void equals() {
        List<String> firstTagList = List.of("first");
        List<String> secondTagList = List.of("first", "second");

        TagsOrFoundPredicate firstPredicate = preparePredicate(firstTagList);
        TagsOrFoundPredicate secondPredicate = preparePredicate(secondTagList);


        assertEquals(firstPredicate, firstPredicate);
        TagsOrFoundPredicate firstPredicateCopy = preparePredicate(firstTagList);
        assertEquals(firstPredicate, firstPredicateCopy);
        assertNotEquals(1, firstPredicate);
        assertNotEquals(null, firstPredicate);
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_tagFound_returnsTrue() {
        TagsOrFoundPredicate predicate = preparePredicate(List.of("car"));
        assertTrue(predicate.test(new PersonBuilder().withTags("car").build()));

        // Multiple keywords
        predicate = preparePredicate(List.of("car", "health"));
        assertTrue(predicate.test(new PersonBuilder().withTags("health").build()));

        // Only one matching keyword
        predicate = preparePredicate(List.of("car", "health"));
        assertTrue(predicate.test(new PersonBuilder().withTags("car", "covid").build()));

        // Mixed-case keywords
        predicate = preparePredicate(List.of("car", "health"));
        assertTrue(predicate.test(new PersonBuilder().withTags("car", "health").build()));
    }

    @Test
    public void test_tagNotFound_returnsFalse() {
        TagsOrFoundPredicate predicate = preparePredicate(List.of());
        assertFalse(predicate.test(new PersonBuilder().withTags("car").build()));

        predicate = preparePredicate(List.of("motorcycle"));
        assertFalse(predicate.test(new PersonBuilder().withTags("car", "health").build()));

        predicate = preparePredicate(List.of("Car", "Health", "CAR", "HEAlth"));
        assertFalse(predicate.test(new PersonBuilder().withTags("car", "health").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> tagList = List.of("tag1", "tag2");
        TagsOrFoundPredicate predicate = preparePredicate(tagList);
        String expected = TagsOrFoundPredicate.class.getCanonicalName() + "{tags=" + TagBuilder.build(tagList) + "}";
        assertEquals(expected, predicate.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TagsOrFoundPredicate}.
     */
    private TagsOrFoundPredicate preparePredicate(List<String> tagList) {
        return new TagsOrFoundPredicate(TagBuilder.build(tagList));
    }

}
