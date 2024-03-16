package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicates.CombinedPredicates;
import seedu.address.model.person.predicates.NameContainsSubstringPredicate;
import seedu.address.model.person.predicates.PhoneContainsSubstringPredicate;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsSubstringPredicate firstPredicate = new NameContainsSubstringPredicate("Alex");
        NameContainsSubstringPredicate secondPredicate = new NameContainsSubstringPredicate("Chris");

        NameContainsSubstringPredicate firstPredicateClone = new NameContainsSubstringPredicate("Alex");

        FindCommand findFirstCommand = new FindCommand(new CombinedPredicates(firstPredicate));
        FindCommand findSecondCommand = new FindCommand(new CombinedPredicates(secondPredicate));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(new CombinedPredicates(firstPredicateClone));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        FindCommand command = new FindCommand(new CombinedPredicates());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalAddressBook().getPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PhoneContainsSubstringPredicate predicate = new PhoneContainsSubstringPredicate("9482");
        FindCommand command = new FindCommand(new CombinedPredicates(predicate));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsSubstringPredicate predicate = new NameContainsSubstringPredicate("Fiona");
        CombinedPredicates combinedPredicates = new CombinedPredicates(predicate);
        FindCommand findCommand = new FindCommand(combinedPredicates);
        String expected = FindCommand.class.getCanonicalName() + "{predicates=" + combinedPredicates + "}";
        assertEquals(expected, findCommand.toString());
    }
}
