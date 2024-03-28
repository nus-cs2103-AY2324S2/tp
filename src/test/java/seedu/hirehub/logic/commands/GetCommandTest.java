package seedu.hirehub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hirehub.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hirehub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hirehub.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.hirehub.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.hirehub.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.hirehub.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.ModelManager;
import seedu.hirehub.model.UserPrefs;
import seedu.hirehub.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) and unit tests for GetCommand.
 */
public class GetCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_getPersonAtIndex_success() {
        String expectedMessage = String.format(GetCommand.MESSAGE_GET_PERSON_SUCCESS, 1);
        GetCommand command = new GetCommand(INDEX_FIRST_PERSON);
        List<Person> lastShownList = expectedModel.getFilteredPersonList();
        Person personToGet = lastShownList.get(INDEX_FIRST_PERSON.getZeroBased());
        Predicate<Person> predicate = x -> personToGet.equals(x);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        GetCommand getCommand = new GetCommand(outOfBoundIndex);

        assertCommandFailure(getCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        GetCommand getCommand = new GetCommand(outOfBoundIndex);

        assertCommandFailure(getCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final GetCommand standardCommand = new GetCommand(INDEX_FIRST_PERSON);
        // same values -> returns true
        GetCommand commandWithSameIndex = new GetCommand(INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameIndex));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new GetCommand(INDEX_SECOND_PERSON)));
    }
}
