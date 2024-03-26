package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicate.ComponentStringPredicate.Component;
import seedu.address.model.person.predicate.ComponentStringPredicate.EndsWith;
import seedu.address.model.person.predicate.ComponentStringPredicate.Has;
import seedu.address.model.person.predicate.ComponentStringPredicate.Hasnt;
import seedu.address.model.person.predicate.ComponentStringPredicate.Is;
import seedu.address.model.person.predicate.ComponentStringPredicate.Isnt;
import seedu.address.model.person.predicate.ComponentStringPredicate.NoWord;
import seedu.address.model.person.predicate.ComponentStringPredicate.StartsWith;
import seedu.address.model.person.predicate.ComponentStringPredicate.Word;
import seedu.address.model.tag.Tag;

class ComponentStringPredicateTest {
    private static final Component STUBBED_COMPONENT = Component.Address;

    /**
     * A Person stub that is used to check ComponentStringPredicate's general
     * input manipulation functions.
     */
    private static class PersonStub extends Person {
        /**
         * Address is used because it has the least number of restrictions.
         *
         * @param address The test address
         */
        public PersonStub(String address) {
            super(new Name("something"), new Phone("123"), new Email("unnecessary@unnecessary.com"),
                    new Address(address), new HashSet<Tag>(List.of(new Tag("unnecessary"))));
        }

        @Override
        public Name getName() {
            throw new AssertionError("This should not be called");
        }

        @Override
        public Phone getPhone() {
            throw new AssertionError("This should not be called");
        }

        @Override
        public Email getEmail() {
            throw new AssertionError("This should not be called");
        }

        @Override
        public Set<Tag> getTags() {
            throw new AssertionError("This should not be called");
        }

        @Override
        public boolean isSamePerson(Person otherPerson) {
            throw new AssertionError("This should not be called");
        }

        @Override
        public boolean equals(Object other) {
            throw new AssertionError("This should not be called");
        }

        @Override
        public int hashCode() {
            throw new AssertionError("This should not be called");
        }

        @Override
        public String toString() {
            throw new AssertionError("This should not be called");
        }
    }

    @Test
    void getInput_normalInput_success() {
        var predicate = new Is("wow", Component.Name);
        assertEquals(predicate.getInput(), "wow");
    }

    @Test
    void constructor_emptyInput_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> new Is("  ", Component.Name));
        assertThrows(AssertionError.class, () -> new Is("", Component.Name));
    }


    @Test
    void extract() {
        final var name = "someone";
        final var phone = "123";
        final var email = "someone@somesite.com";
        final var address = "somewhere";
        final var tag = "unnecessary";
        var testPerson = new Person(new Name(name), new Phone(phone), new Email(email),
                new Address(address), new HashSet<>(List.of(new Tag(tag))));

        assertTrue(new Is(name, Component.Name).test(testPerson));
        assertTrue(new Is(phone, Component.Phone).test(testPerson));
        assertTrue(new Is(email, Component.Email).test(testPerson));
        assertTrue(new Is(address, Component.Address).test(testPerson));
        assertTrue(new Is(tag, Component.Tags).test(testPerson));
    }

    @Nested
    class IsTest {
        private final Is is = new Is("test", STUBBED_COMPONENT);

        @Test
        void test_exactMatch_success() {
            assertTrue(is.test(new PersonStub("test")));
        }

        @Test
        void test_notExactMatch_failure() {
            // contains the input
            assertFalse(is.test(new PersonStub("tester")));
            assertFalse(is.test(new PersonStub("attest")));
            assertFalse(is.test(new PersonStub("test this")));

            // does not contain the input
            assertFalse(is.test(new PersonStub("empty")));

            // contains parts of the input
            assertFalse(is.test(new PersonStub("teaser")));
            assertFalse(is.test(new PersonStub("cast")));
        }
    }

    @Nested
    class IsntTest {
        private final Isnt isnt = new Isnt("test", STUBBED_COMPONENT);

        @Test
        void test_exactMatch_failure() {
            assertFalse(isnt.test(new PersonStub("test")));
        }

        @Test
        void test_notExactMatch_success() {
            // contains the input
            assertTrue(isnt.test(new PersonStub("tester")));
            assertTrue(isnt.test(new PersonStub("attest")));
            assertTrue(isnt.test(new PersonStub("test this")));

            // does not contain the input
            assertTrue(isnt.test(new PersonStub("empty")));

            // contains parts of the input
            assertTrue(isnt.test(new PersonStub("teaser")));
            assertTrue(isnt.test(new PersonStub("cast")));
        }

    }

    @Nested
    class HasTest {
        private final Has has = new Has("test", STUBBED_COMPONENT);

        @Test
        void test_containsInput_success() {
            // exact match
            assertTrue(has.test(new PersonStub("test")));

            // word match
            assertTrue(has.test(new PersonStub("some test")));
            assertTrue(has.test(new PersonStub("test some")));

            // sub-word match
            assertTrue(has.test(new PersonStub("tester")));
        }

        @Test
        void test_doesntContainInput_failure() {
            assertFalse(has.test(new PersonStub("missing")));
        }

    }

    @Nested
    class HasntTest {
        private final Hasnt hasnt = new Hasnt("test", STUBBED_COMPONENT);

        @Test
        void test_containsInput_failure() {
            // exact match
            assertFalse(hasnt.test(new PersonStub("test")));

            // word match
            assertFalse(hasnt.test(new PersonStub("some test")));
            assertFalse(hasnt.test(new PersonStub("test some")));

            // sub-word match
            assertFalse(hasnt.test(new PersonStub("tester")));
        }

        @Test
        void test_doesNotContainInput_success() {
            assertTrue(hasnt.test(new PersonStub("missing")));
        }

    }

    @Nested
    class StartsWithTest {
        private final StartsWith startsWith = new StartsWith("test", STUBBED_COMPONENT);

        @Test
        void test_startsWithInput_success() {
            assertTrue(startsWith.test(new PersonStub("tester")));
        }

        @Test
        void test_doesNotStartWithInput_failure() {
            // ends with
            assertFalse(startsWith.test(new PersonStub("attest")));

            // somewhere inside
            assertFalse(startsWith.test(new PersonStub("contestant")));

            // missing
            assertFalse(startsWith.test(new PersonStub("missing")));
        }

    }

    @Nested
    class EndsWithTest {
        private final EndsWith endsWith = new EndsWith("test", STUBBED_COMPONENT);

        @Test
        void test_endsWithInput_success() {
            assertTrue(endsWith.test(new PersonStub("attest")));
        }

        @Test
        void test_doesNotEndWithInput_failure() {
            // ends with
            assertFalse(endsWith.test(new PersonStub("tester")));

            // somewhere inside
            assertFalse(endsWith.test(new PersonStub("contestant")));

            // missing
            assertFalse(endsWith.test(new PersonStub("missing")));
        }

    }

    @Nested
    class WordTest {
        private final Word word = new Word("one two 3", STUBBED_COMPONENT);

        @Test
        void test_matchingInputs_success() {
            // individual
            assertTrue(word.test(new PersonStub("one")));
            assertTrue(word.test(new PersonStub("two")));

            // more than one match in any order
            assertTrue(word.test(new PersonStub("one two")));
            assertTrue(word.test(new PersonStub("two one three")));

            // non-matching alongside matching
            assertTrue(word.test(new PersonStub("four one three")));

            // duplicate matches
            assertTrue(word.test(new PersonStub("one one one")));

            // symbols aren't in word ends
            assertTrue(word.test(new PersonStub("one?")));
            assertTrue(word.test(new PersonStub(".two")));
            assertTrue(word.test(new PersonStub("!3")));
            assertTrue(word.test(new PersonStub("file.one")));
        }

        @Test
        void test_nonMatchingInputs_failure() {
            var predicate = new Word("one two 3", STUBBED_COMPONENT);

            // can't be part of a word
            assertFalse(predicate.test(new PersonStub("onetwo3")));

            // doesn't match if no words exist
            assertFalse(predicate.test(new PersonStub("2")));
        }

        /**
         * Indirect test of the makeWordsPattern.
         */
        @Test
        void makeWordsPattern_specialRegexCharacters_quoted() {
            var regexCharPredicate = new Word("wo?w", STUBBED_COMPONENT);

            var shouldNotMatch = new PersonStub("wow ww wo?wb");
            assertFalse(regexCharPredicate.test(shouldNotMatch));

            var shouldMatch = new PersonStub("wo?w");
            assertTrue(regexCharPredicate.test(shouldMatch));
        }
    }

    @Nested
    class NoWordTest {
        private final NoWord noWord = new NoWord("one two 3", STUBBED_COMPONENT);

        @Test
        void test_matchingInputs_success() {
            assertTrue(noWord.test(new PersonStub("2 three four")));
        }

        @Test
        void test_nonMatchingInputs_failure() {
            // different order
            assertFalse(noWord.test(new PersonStub("two 3 one")));

            // non-matching alongside matching
            assertFalse(noWord.test(new PersonStub("four one three 3 two")));

            // duplicate matches
            assertFalse(noWord.test(new PersonStub("one one one two two 3 3 3")));

            // symbols aren't in word ends
            assertFalse(noWord.test(new PersonStub("file.one?")));
            assertFalse(noWord.test(new PersonStub("two!")));
            assertFalse(noWord.test(new PersonStub("'3'")));

            // Not all
            assertFalse(noWord.test(new PersonStub("one")));
            assertFalse(noWord.test(new PersonStub("3 two")));
        }
    }
}
