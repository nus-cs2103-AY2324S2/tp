package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalEvents.getBingoEvent;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteEventCommand}.
 */
public class DeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_selectModel_throwsCommandException() {
        SelectCommand selectCommand = new SelectCommand(Index.fromZeroBased(1));

        assertThrows(CommandException.class, () -> new DeleteEventCommand(Index.fromZeroBased(1)).execute(model));
    }

    @Test
    public void execute_deleteEvent_success() throws CommandException {
        ModelManager model = new ModelManager();
        model.setEventBook(getTypicalEventBook());
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(Index.fromZeroBased(0));
        CommandResult commandResult = deleteEventCommand.execute(model);
        assertEquals(String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(getBingoEvent())),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstEventCommand = new DeleteEventCommand(INDEX_FIRST_EVENT);
        DeleteEventCommand deleteSecondEventCommand = new DeleteEventCommand(INDEX_SECOND_EVENT);

        // same object -> returns true
        assertTrue(deleteFirstEventCommand.equals(deleteFirstEventCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstEventCommandCopy = new DeleteEventCommand(INDEX_FIRST_EVENT);
        assertTrue(deleteFirstEventCommand.equals(deleteFirstEventCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstEventCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstEventCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstEventCommand.equals(deleteSecondEventCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(targetIndex);
        String expected = DeleteEventCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteEventCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no event.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
