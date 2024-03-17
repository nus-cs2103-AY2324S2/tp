package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneMatchesPredicateTest {
    @Test
    public void test_matchingPhone_returnsTrue() {
        Person person = new PersonBuilder().withPhone("91237654").build();

        PhoneMatchesPredicate fullMatch = new PhoneMatchesPredicate("91237654");
        assertTrue(fullMatch.test(person));

        PhoneMatchesPredicate partialPrefixMatch = new PhoneMatchesPredicate("9123");
        assertTrue(partialPrefixMatch.test(person));
    }

    @Test void test_noMatchingPhone_returnsFalse() {
        Person person = new PersonBuilder().withPhone("91237654").build();

        PhoneMatchesPredicate noMatch = new PhoneMatchesPredicate("98761234");
        assertFalse(noMatch.test(person));

        PhoneMatchesPredicate partialMiddleMatch = new PhoneMatchesPredicate("123");
        assertFalse(partialMiddleMatch.test(person));
    }
}
