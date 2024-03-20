package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.StarsLessThanPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStarsLessThanCommand}.
 */
public class FindStarsLessThanCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StarsLessThanPredicate firstPredicate =
                new StarsLessThanPredicate(1);
        StarsLessThanPredicate secondPredicate =
                new StarsLessThanPredicate(2);

        FindStarsLessThanCommand findFirstCommand = new FindStarsLessThanCommand(firstPredicate);
        FindStarsLessThanCommand findSecondCommand = new FindStarsLessThanCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStarsLessThanCommand findFirstCommandCopy = new FindStarsLessThanCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroUpperBound_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        StarsLessThanPredicate predicate = preparePredicate(0);
        FindStarsLessThanCommand command = new FindStarsLessThanCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_infiniteUpperBound_allPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW,
                expectedModel.getFilteredPersonList().size());
        StarsLessThanPredicate predicate = preparePredicate(Integer.MAX_VALUE);
        FindStarsLessThanCommand command = new FindStarsLessThanCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        StarsLessThanPredicate predicate = new StarsLessThanPredicate(1);
        FindStarsLessThanCommand findStarsLessThanCommand = new FindStarsLessThanCommand(predicate);
        String expected = FindStarsLessThanCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findStarsLessThanCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private StarsLessThanPredicate preparePredicate(Integer i) {
        return new StarsLessThanPredicate(i);
    }

}
