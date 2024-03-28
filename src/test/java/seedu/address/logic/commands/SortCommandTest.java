package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonInRange;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalReversedAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.ArticleBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
class SortCommandTest {

    private Model model = new ModelManager(getTypicalReversedAddressBook(), new ArticleBook(), new UserPrefs());
    private final Prefix invalidPrefix = new Prefix("z/");

    @Test
    public void execute_validPrefixUnfilteredList_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_NAME.getPrefix());

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new ArticleBook(), new UserPrefs());
        expectedModel.sortAddressBook(PREFIX_NAME.getPrefix());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPrefixUnfilteredList_throwsCommandException() {
        SortCommand sortCommand = new SortCommand(invalidPrefix.getPrefix());

        assertCommandFailure(sortCommand, model, Messages.MESSAGE_INVALID_SORTING_PREFIX);
    }

    @Test
    public void execute_validPrefixFilteredList_success() {
        showPersonInRange(model, INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);

        SortCommand sortCommand = new SortCommand(PREFIX_NAME.getPrefix());

        Model expectedModel = new ModelManager(model.getAddressBook(), new ArticleBook(), new UserPrefs());
        expectedModel.sortAddressBook(PREFIX_NAME.getPrefix());

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_invalidPrefixFilteredList_throwsCommandException() {
        showPersonInRange(model, INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);

        SortCommand sortCommand = new SortCommand(invalidPrefix.getPrefix());

        assertCommandFailure(sortCommand, model, Messages.MESSAGE_INVALID_SORTING_PREFIX);
    }

    @Test
    public void equals() {
        SortCommand fSortCommand = new SortCommand(PREFIX_NAME.getPrefix());
        SortCommand sSortCommand = new SortCommand(PREFIX_ADDRESS.getPrefix());

        // same object -> returns true
        assertTrue(fSortCommand.equals(fSortCommand));

        // same values -> returns true
        SortCommand sortCommandCopy = new SortCommand(PREFIX_NAME.getPrefix());
        assertTrue(fSortCommand.equals(sortCommandCopy));

        // different types -> returns false
        assertFalse(fSortCommand.equals(1));

        // null -> returns false
        assertFalse(fSortCommand.equals(null));

        // different person -> returns false
        assertFalse(fSortCommand.equals(sSortCommand));
    }

    @Test
    public void toStringMethod() {
        Prefix namePrefix = PREFIX_NAME;
        SortCommand sortCommand = new SortCommand(namePrefix.getPrefix());
        String expected = SortCommand.class.getCanonicalName() + "{prefix=" + namePrefix.getPrefix() + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
