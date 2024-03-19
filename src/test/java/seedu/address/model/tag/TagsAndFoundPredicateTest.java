package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TagBuilder;


public class TagsAndFoundPredicateTest {

    @Test
    public void equals() {
        List<String> firstTagList = List.of("first");
        List<String> secondTagList = List.of("first", "second");

        TagsAndFoundPredicate firstPredicate = preparePredicate(firstTagList);
        TagsAndFoundPredicate secondPredicate = preparePredicate(secondTagList);

        assertTrue(firstPredicate.equals(firstPredicate));
        TagsAndFoundPredicate firstPredicateCopy = preparePredicate(firstTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));
        assertFalse(firstPredicate.equals(1));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagFound_returnsTrue() {
        TagsAndFoundPredicate predicate = preparePredicate(List.of("car"));
        assertTrue(predicate.test(new PersonBuilder().withTags("car").build()));

        predicate = preparePredicate(List.of("car", "health"));
        assertTrue(predicate.test(new PersonBuilder().withTags("car", "health").build()));

        predicate = preparePredicate(List.of("car", "health"));
        assertTrue(predicate.test(new PersonBuilder().withTags("car", "health", "flight").build()));

        predicate = preparePredicate(List.of());
        assertTrue(predicate.test(new PersonBuilder().withTags("car").build()));
    }

    @Test
    public void test_tagNotFound_returnsFalse() {
        TagsAndFoundPredicate predicate = preparePredicate(List.of("motorcycle"));
        assertFalse(predicate.test(new PersonBuilder().withTags("car", "health").build()));

        predicate = preparePredicate(List.of("Car", "Health"));
        assertFalse(predicate.test(new PersonBuilder().withTags("car", "health").build()));

        predicate = preparePredicate(List.of("car", "health"));
        assertFalse(predicate.test(new PersonBuilder().withTags("health").build()));

        predicate = preparePredicate(List.of("car", "health"));
        assertFalse(predicate.test(new PersonBuilder().withTags("car", "covid").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> tagList = List.of("tag1", "tag2");
        TagsAndFoundPredicate predicate = preparePredicate(tagList);
        String expected = TagsAndFoundPredicate.class.getCanonicalName() + "{tags=" + TagBuilder.build(tagList) + "}";
        assertEquals(expected, predicate.toString());
    }

    /**
     * Parses {@code userInput} into a {@code TagsAndFoundPredicate}.
     */
    private TagsAndFoundPredicate preparePredicate(List<String> tagList) {
        return new TagsAndFoundPredicate(TagBuilder.build(tagList));
    }

}
