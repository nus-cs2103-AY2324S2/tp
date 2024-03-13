package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import staffconnect.model.tag.Tag;
import staffconnect.testutil.PersonBuilder;

public class PersonHasTagPredicateTest {

    @Test
    public void equals() {
        Tag firstPredicateTag = new Tag("first");
        Tag secondPredicateTag = new Tag("second");

        PersonHasTagPredicate firstPredicate = new PersonHasTagPredicate(firstPredicateTag);
        PersonHasTagPredicate secondPredicate = new PersonHasTagPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagPredicate firstPredicateCopy = new PersonHasTagPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personHasTag_returnsTrue() {
        // predicate set to track "tester" tag
        PersonHasTagPredicate predicate = new PersonHasTagPredicate(new Tag("tester"));

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
    public void test_personDoesNotHaveTag_returnsFalse() {
        // predicate set to track "tester" tag
        PersonHasTagPredicate predicate = new PersonHasTagPredicate(new Tag("tester"));

        // person does not have tag "tester"
        assertFalse(predicate.test(new PersonBuilder().withTags("tester2").build()));
    }

    @Test
    public void toStringMethod() {
        Tag tag = new Tag("hello");
        PersonHasTagPredicate predicate = new PersonHasTagPredicate(tag);

        String expected = PersonHasTagPredicate.class.getCanonicalName() + "{tag name=" + tag + "}";
        assertEquals(expected, predicate.toString());
    }
}
