package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDIO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.PersonBuilder;

public class PersonDetailContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        PersonDetailContainsKeywordPredicate firstPredicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, firstPredicateKeyword);
        PersonDetailContainsKeywordPredicate secondPredicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, secondPredicateKeyword);
        PersonDetailContainsKeywordPredicate thirdPredicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, firstPredicateKeyword);
        PersonDetailContainsKeywordPredicate fourthPredicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // same values -> returns true
        PersonDetailContainsKeywordPredicate firstPredicateCopy =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // same prefix, different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // same keyword, different prefix -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different prefix and keyword -> returns false
        assertFalse(firstPredicate.equals(fourthPredicate));

    }

    @Test
    public void test_nameContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "Alice");

        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "aLiCe");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "Adam");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match email, but does not match name
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "alice@gmail.com");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@gmail.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_emailContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "alice@gmail.com");

        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "aLiCe@GMAIl.cOm");
        assertTrue(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "adam@gmail.com");
        assertFalse(predicate.test(new PersonBuilder().withEmail("alice@gmail.com").build()));

        // Keywords match name, but does not match email
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "Alice");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alisu@gmail.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_phoneContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_PHONE, "123456");

        assertTrue(predicate.test(new PersonBuilder().withPhone("123456").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_PHONE, "123456");

        assertFalse(predicate.test(new PersonBuilder().withPhone("1232346").build()));

        // Keywords match email, but does not match phone
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_PHONE, "alice@gmail.com");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@gmail.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_addressContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_ADDRESS, "Main Street");

        assertTrue(predicate.test(new PersonBuilder().withAddress("Main Street").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_ADDRESS, "mAin STReet");
        assertTrue(predicate.test(new PersonBuilder().withAddress("Main Street").build()));
    }

    @Test
    public void test_addressDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_ADDRESS, "Side Street");

        assertFalse(predicate.test(new PersonBuilder().withAddress("Main Street").build()));

        // Keywords match phone, but does not match address
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_ADDRESS, "12345");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_tagContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_TAG, "Friend");

        assertTrue(predicate.test(new PersonBuilder().withTags("Friend").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_TAG, "fRieNd");
        assertTrue(predicate.test(new PersonBuilder().withTags("Friend").build()));
    }

    @Test
    public void test_tagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_TAG, "Friend");

        assertFalse(predicate.test(new PersonBuilder().withTags("Family").build()));

        // Keywords match address, but does not match tag
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_TAG, "Main Street");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Friend").build()));
    }

    @Test
    public void test_matricContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_MATRIC_NUMBER, "A1234567A");

        assertTrue(predicate.test(new PersonBuilder().withMatric("A1234567A").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_MATRIC_NUMBER, "a1234567a");
        assertTrue(predicate.test(new PersonBuilder().withMatric("A1234567A").build()));
    }

    @Test
    public void test_matricDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_MATRIC_NUMBER, "A1234567A");

        assertFalse(predicate.test(new PersonBuilder().withMatric("A1234567B").build()));

        // Keywords match tag, but does not match matric
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_MATRIC_NUMBER, "Friend");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("Friend").build()));
    }

    @Test
    public void test_reflectionContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_REFLECTION, "R1");

        assertTrue(predicate.test(new PersonBuilder().withReflection("R1").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_REFLECTION, "r1");
        assertTrue(predicate.test(new PersonBuilder().withReflection("R1").build()));
    }

    @Test
    public void test_reflectionDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_REFLECTION, "R1");

        assertFalse(predicate.test(new PersonBuilder().withReflection("R2").build()));

        // Keywords match matric, but does not match reflection
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_REFLECTION, "A1234567A");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withMatric("A1234567A").withReflection("R1").build()));
    }

    @Test
    public void test_studioContainsKeyword_returnsTrue() {
        // One keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_STUDIO, "S1");

        assertTrue(predicate.test(new PersonBuilder().withStudio("S1").build()));
        // Mixed-case keywords
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_STUDIO, "s1");
        assertTrue(predicate.test(new PersonBuilder().withStudio("S1").build()));
    }

    @Test
    public void test_studioDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_STUDIO, "S1");

        assertFalse(predicate.test(new PersonBuilder().withStudio("S2").build()));

        // Keywords match reflection, but does not match studio
        predicate = new PersonDetailContainsKeywordPredicate(PREFIX_STUDIO, "R1");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withReflection("R1").withStudio("S1").build()));
    }

    @Test
    public void test_invalidPrefix_returnsFalse() {
        // Non-matching keyword

        Prefix invalidPrefix = new Prefix("i/");
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(invalidPrefix, "abc");

        assertFalse(predicate.test(new PersonBuilder().withTags("Family").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "example";
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, keyword);

        String expected = PersonDetailContainsKeywordPredicate.class.getCanonicalName()
                          + "{prefix=" + PREFIX_NAME + ", "
                          + "keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
