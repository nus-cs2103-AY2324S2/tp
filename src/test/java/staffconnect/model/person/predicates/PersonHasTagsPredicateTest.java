package staffconnect.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import staffconnect.model.tag.Tag;
import staffconnect.testutil.PersonBuilder;

public class PersonHasTagsPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTag = new HashSet<Tag>(Arrays.asList(new Tag("first")));
        Set<Tag> secondPredicateTag = new HashSet<Tag>(Arrays.asList(new Tag("second")));

        PersonHasTagsPredicate firstPredicate = new PersonHasTagsPredicate(firstPredicateTag);
        PersonHasTagsPredicate secondPredicate = new PersonHasTagsPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagsPredicate firstPredicateCopy = new PersonHasTagsPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personHasTag_returnsTrue() {
        // predicate set to track "tester" tag
        Set<Tag> tag = new HashSet<Tag>(Arrays.asList(new Tag("tester")));
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(tag);

        // person only has tag "tester"
        assertTrue(predicate.test(new PersonBuilder().withTags("tester").build()));

        // person has multiple tags and has "tester"
        assertTrue(predicate.test(new PersonBuilder().withTags("tester", "tester2").build()));

        // case-insensitivity checks
        assertTrue(predicate.test(new PersonBuilder().withTags("tesTER").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("TESTER").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("tEsTeR").build()));
    }

    @Test
    public void test_personHasMultipleTags_returnsTrue() {
        // predicate set to track "tester", "tester2" tags
        Set<Tag> multipleTags = new HashSet<Tag>(Arrays.asList(new Tag("tester"), new Tag("tester2")));
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(multipleTags);

        // person has multiple tags and has "tester", "tester2"
        assertTrue(predicate.test(new PersonBuilder().withTags("tester", "tester2").build()));

        // case-insensitivity checks
        assertTrue(predicate.test(new PersonBuilder().withTags("tesTER", "tesTER2").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("TESTER", "tester2").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("tEsTeR", "TeStEr2").build()));
    }

    @Test
    public void test_personDoesNotHaveTag_returnsFalse() {
        // predicate set to track "tester" tag
        Set<Tag> tag = new HashSet<Tag>(Arrays.asList(new Tag("tester")));
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(tag);

        // person does not have tag "tester"
        assertFalse(predicate.test(new PersonBuilder().withTags("tester2").build()));
    }

    @Test
    public void test_personDoesNotHaveMultipleTags_returnsFalse() {
        // predicate set to track "tester", "tester2", "tester3" tags
        Set<Tag> multipleTags = new HashSet<Tag>(
                Arrays.asList(new Tag("tester"), new Tag("tester2"), new Tag("tester3")));
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(multipleTags);

        // person only has 1 tag
        assertFalse(predicate.test(new PersonBuilder().withTags("tester").build()));

        // case-insensitivity checks
        assertFalse(predicate.test(new PersonBuilder().withTags("tesTER", "tesTER2").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("TESTER", "tester2").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags("tEsTeR", "TeStEr2").build()));
    }

    @Test
    public void toStringMethod() {
        Set<Tag> tag = new HashSet<Tag>(Arrays.asList(new Tag("hello")));
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(tag);

        String expected = PersonHasTagsPredicate.class.getCanonicalName() + "{tag names=" + tag + "}";
        assertEquals(expected, predicate.toString());
    }
}
