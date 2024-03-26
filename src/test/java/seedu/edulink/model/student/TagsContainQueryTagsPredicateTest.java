package seedu.edulink.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.edulink.model.tag.Tag;
import seedu.edulink.testutil.PersonBuilder;

public class TagsContainQueryTagsPredicateTest {

    @Test
    public void test_tagsMatchesQueryTags_returnsTrue() {

        Tag testTag1 = new Tag("TA");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);

        // Match
        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertTrue(predicate.test(new PersonBuilder().withTags("TA").build()));

        // Mixed-case keywords
        Tag newTestTag1 = new Tag("ta");
        queryTags.remove(testTag1);
        queryTags.add(newTestTag1);
        predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertTrue(predicate.test(new PersonBuilder().withTags("TA").build()));

        // 2 Matches
        Tag testTag2 = new Tag("Smart");
        queryTags.add(testTag2);
        predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertTrue(predicate.test(new PersonBuilder().withTags("TA", "Smart").build()));
    }

    @Test
    public void test_tagsDoesNotMatchesQueryTags_returnsFalse() {
        Tag testTag1 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertFalse(predicate.test(new PersonBuilder().withTags("Smarter").build()));
    }

    @Test
    public void test_ontTagDoesNotMatchesQueryTags_returnsFalse() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertFalse(predicate.test(new PersonBuilder().withTags("TA", "Helpful", "Assistance").build()));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate1 = new TagsContainQueryTagsPredicate(queryTags);
        TagsContainQueryTagsPredicate predicate2 = new TagsContainQueryTagsPredicate(queryTags);
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags1 = new HashSet<>();
        Set<Tag> queryTags2 = new HashSet<>();
        queryTags1.add(testTag1);
        queryTags2.add(testTag2);

        TagsContainQueryTagsPredicate predicate1 = new TagsContainQueryTagsPredicate(queryTags1);
        TagsContainQueryTagsPredicate predicate2 = new TagsContainQueryTagsPredicate(queryTags2);
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void equals_differentClasses_returnsFalse() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertFalse(predicate.equals(queryTags));
    }

    @Test
    public void equals_null_returnsFalse() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        assertFalse(predicate.equals(null));
    }

    @Test
    public void toString_validInput_returnsString() {
        Tag testTag1 = new Tag("TA");
        Tag testTag2 = new Tag("Smart");
        Set<Tag> queryTags = new HashSet<>();
        queryTags.add(testTag1);
        queryTags.add(testTag2);

        TagsContainQueryTagsPredicate predicate = new TagsContainQueryTagsPredicate(queryTags);
        String expected = TagsContainQueryTagsPredicate.class.getCanonicalName() + "{Tags: =[[TA], [Smart]]}";
        assertEquals(expected, predicate.toString());
    }
}
