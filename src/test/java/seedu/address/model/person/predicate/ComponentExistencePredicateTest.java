package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.predicate.ComponentExistencePredicate.Component;
import static seedu.address.model.person.predicate.ComponentExistencePredicate.None;

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
import seedu.address.model.person.predicate.ComponentExistencePredicate.Some;
import seedu.address.model.tag.Tag;

class ComponentExistencePredicateTest {
    private static final Component STUBBED_COMPONENT = Component.Tags;

    private final Set<Tag> nonEmpty = new HashSet<>(List.of(new Tag("hello")));
    private final Set<Tag> empty = new HashSet<>();

    /**
     * A Person stub that is used to unit test ComponentExistencePredicate.
     */
    private static class PersonStub extends Person {
        /**
         * @param tags The tags to test with.
         */
        public PersonStub(Set<Tag> tags) {
            super(new Name("something"), new Phone("123"), new Email("unnecessary@unnecessary.com"),
                    new Address("somewhere"), tags);
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
        public Address getAddress() {
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

    @Nested
    public class SomeTest {
        private final Some some = new Some(STUBBED_COMPONENT);

        @Test
        void test_exists_success() {
            assertTrue(some.test(new PersonStub(nonEmpty)));
        }

        @Test
        void test_doesNotExist_failure() {
            assertFalse(some.test(new PersonStub(empty)));
        }
    }

    @Nested
    public class NoneTest {
        private final None none = new None(STUBBED_COMPONENT);

        @Test
        void test_exists_failure() {
            assertFalse(none.test(new PersonStub(nonEmpty)));
        }

        @Test
        void test_doesNotExist_success() {
            assertTrue(none.test(new PersonStub(empty)));
        }
    }
}
