package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ViewClientCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ViewClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        ViewClientCommand firstViewCommand = new ViewClientCommand(INDEX_FIRST_PERSON);
        ViewClientCommand secondViewCommand = new ViewClientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same values -> returns true
        ViewClientCommand firstViewCommandCopy = new ViewClientCommand(INDEX_FIRST_PERSON);
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

        // different person -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }
    @Test
    public void execute_viewClient_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS + INDEX_FIRST_PERSON.getOneBased());
        assertCommandSuccess(new ViewClientCommand(INDEX_FIRST_PERSON), model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ViewClientCommand viewCommand = new ViewClientCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewClientCommand viewCommand = new ViewClientCommand(targetIndex);
        String expected = ViewClientCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
