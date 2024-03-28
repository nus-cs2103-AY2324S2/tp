package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class TagMatchesPredicateTest {

    @Test
    void test_tagMatchesPredicate_returnsTrue() {
        TagMatchesPredicate predicate = new TagMatchesPredicate(new Tag("tag"));
        assertTrue(predicate.test(new PersonBuilder().withTags("tag", "tagg", "tags").build()));
    }

    @Test
    void test_tagMatchesPredicate_returnsFalse() {
        TagMatchesPredicate predicate = new TagMatchesPredicate(new Tag("tag"));
        assertFalse(predicate.test(new PersonBuilder().withTags("tags", "tagg").build()));
    }

    @Test
    void equals() {
        TagMatchesPredicate firstPredicateTag = new TagMatchesPredicate(new Tag("tag1"));
        TagMatchesPredicate secondPredicateTag = new TagMatchesPredicate(new Tag("tag2"));
        TagMatchesPredicate thirdPredicateTag = new TagMatchesPredicate(new Tag("tag1"));

        assertTrue(firstPredicateTag.equals(firstPredicateTag));

        assertTrue(firstPredicateTag.equals(thirdPredicateTag));

        assertFalse(firstPredicateTag.equals(secondPredicateTag));

        assertFalse(firstPredicateTag.equals(null));
    }

    @Test
    void toStringMethod() {
        String keywords = "taggg";
        TagMatchesPredicate predicate = new TagMatchesPredicate(new Tag(keywords));
        String expected = TagMatchesPredicate.class.getCanonicalName() + "{tag=[" + keywords + "]}";
        assertEquals(expected, predicate.toString());
    }

}
