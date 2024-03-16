package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailMatchesPredicateTest {
    @Test
    public void test_emailMatches_returnsTrue() {
        EmailMatchesPredicate fullMatch = new EmailMatchesPredicate("bobbyApples@gmail.com");
        Person person = new PersonBuilder().withEmail("bobbyApples@gmail.com").build();
        assertTrue(fullMatch.test(person));

        EmailMatchesPredicate partialMatch = new EmailMatchesPredicate("Apples");
        assertTrue(partialMatch.test(person));

        EmailMatchesPredicate diffCaseMatch = new EmailMatchesPredicate("aPPleS");
        assertTrue(diffCaseMatch.test(person));

        EmailMatchesPredicate extraSpaces = new EmailMatchesPredicate(" apples ");
        assertTrue(extraSpaces.test(person));
    }

    @Test
    public void test_emailNoMatch_returnsFalse() {
        Person person = new PersonBuilder().withEmail("bobbyApples@gmail.com").build();

        EmailMatchesPredicate noPartialMatch = new EmailMatchesPredicate("WobblyApples");
        assertFalse(noPartialMatch.test(person));

        EmailMatchesPredicate spacesInBetween = new EmailMatchesPredicate("bobby apples ");
        assertFalse(spacesInBetween.test(person));
    }
}
