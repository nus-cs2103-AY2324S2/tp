package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagMatchesPredicateTest {
    @Test
    public void test_tagMatches_returnsTrue() {
        Person person = new PersonBuilder().withTag(Tag.TagType.TA.toString()).build();

        TagMatchesPredicate validTagPredicate = new TagMatchesPredicate(Tag.TagType.TA.toString());
        assertTrue(validTagPredicate.test(person));
    }

    @Test
    public void test_tagNoMatch_returnsFalse() {
        Person person = new PersonBuilder().withTag(Tag.TagType.Professor.toString()).build();

        TagMatchesPredicate noMatchPredicate = new TagMatchesPredicate(Tag.TagType.TA.toString());
        assertFalse(noMatchPredicate.test(person));

        TagMatchesPredicate gibberishPredicate = new TagMatchesPredicate("GARBAGE_VALUE");
        assertFalse(gibberishPredicate.test(person));
    }

    @Test
    public void test_toString() {
        String keyword = Tag.TagType.TA.name();
        TagMatchesPredicate tagMatchesPredicate = new TagMatchesPredicate(keyword);
        String expected = TagMatchesPredicate.class.getCanonicalName() + "{tagKeyword=" + keyword + "}";
        assertEquals(tagMatchesPredicate.toString(), expected);
    }
}
