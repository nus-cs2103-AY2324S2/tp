package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonHasTagPredicate;
import seedu.address.testutil.TestUtil;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() throws Exception {
        PersonHasTagPredicate firstPredicate =
                new PersonHasTagPredicate(TestUtil.stringsToTags(Collections.singletonList("first")));
        PersonHasTagPredicate secondPredicate =
                new PersonHasTagPredicate(TestUtil.stringsToTags(Collections.singletonList("second")));

        SortCommand sortFirstCommand = new SortCommand(firstPredicate);
        SortCommand sortSecondCommand = new SortCommand(secondPredicate);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand findFirstCommandCopy = new SortCommand(firstPredicate);
        assertTrue(sortFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() throws Exception {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagPredicate predicate = preparePredicate(" ");
        SortCommand command = new SortCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeywords_onePersonFound() throws Exception {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonHasTagPredicate predicate = preparePredicate("TAs");
        SortCommand command = new SortCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JOHN), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeywords_multiplePersonsFound() throws Exception {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagPredicate predicate = preparePredicate("friends");
        SortCommand command = new SortCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_onePersonFound() throws Exception {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonHasTagPredicate predicate = preparePredicate("Acquaintances TAs CCA");
        SortCommand command = new SortCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JOHN), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() throws Exception {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        PersonHasTagPredicate predicate = preparePredicate("friends TAs CCA");
        SortCommand command = new SortCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL, JOHN), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() throws Exception {
        PersonHasTagPredicate predicate = new PersonHasTagPredicate(TestUtil.stringsToTags(Arrays.asList("keyword")));
        SortCommand sortCommand = new SortCommand(predicate);
        String expected = SortCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, sortCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasTagPredicate}.
     */
    private PersonHasTagPredicate preparePredicate(String userInput) throws Exception {
        return new PersonHasTagPredicate(TestUtil.stringsToTags(Arrays.asList(userInput.split("\\s+"))));
    }
}
