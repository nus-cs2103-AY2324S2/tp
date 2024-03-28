package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;



public class SelectCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SelectCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new SelectCommand(Index.fromZeroBased(0)).execute(null));
    }
    @Test
    public void eventIndex_outOfRange_throwsCommandException() {
        ModelManager model = new ModelManager();
        model.setEventBook(getTypicalEventBook());
        assertThrows(CommandException.class, () -> new SelectCommand(Index.fromZeroBased(100)).execute(model));
    }

    @Test
    public void equals_sameIndex_returnsTrue() {
        SelectCommand one = new SelectCommand(Index.fromZeroBased(5));
        SelectCommand two = new SelectCommand(Index.fromZeroBased(5));
        assertEquals(one, two);
    }

    @Test
    public void equals_handles_null() {
        SelectCommand one = new SelectCommand(Index.fromZeroBased(5));
        assertNotEquals(null, one);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        SelectCommand one = new SelectCommand(Index.fromZeroBased(5));
        assertEquals(one, one);
    }

    @Test
    public void execute_selectSuccessful() throws CommandException {
        ModelManager model = new ModelManager();
        model.setEventBook(getTypicalEventBook());
        SelectCommand selectCommand = new SelectCommand(Index.fromZeroBased(0));
        CommandResult commandResult = selectCommand.execute(model);
        assertEquals(String.format(SelectCommand.MESSAGE_SELECT_EVENT_SUCCESS,
                        Messages.format(model.getEventBook().getEventList().get(0))),
                            commandResult.getFeedbackToUser());
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        SelectCommand selectCommand = new SelectCommand(targetIndex);
        String expected = SelectCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, selectCommand.toString());
    }

}
