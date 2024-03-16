package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalEvents;



public class SelectCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SelectCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ModelManager model = new ModelManager();
        model.setEventBook(TypicalEvents.getTypicalEventBook());
        assertThrows(NullPointerException.class, () -> new SelectCommand(Index.fromZeroBased(0)).execute(null));
    }
    @Test
    public void eventIndex_outOfRange_throwsCommandException() {
        ModelManager model = new ModelManager();
        model.setEventBook(TypicalEvents.getTypicalEventBook());
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
        model.setEventBook(TypicalEvents.getTypicalEventBook());
        SelectCommand selectCommand = new SelectCommand(Index.fromZeroBased(0));
        CommandResult commandResult = selectCommand.execute(model);
        assertEquals(String.format(SelectCommand.MESSAGE_SELECT_EVENT_SUCCESS,
                        Messages.format(model.getEventBook().getEventList().get(0))),
                            commandResult.getFeedbackToUser());
    }

    @Test
    public void toStringMethod() {
        SelectCommand selectCommand = new SelectCommand(Index.fromZeroBased(0));
        String expected = new ToStringBuilder(selectCommand)
                .add("targetIndex", selectCommand.targetIndex) // Accessing targetIndex via selectCommand
                .toString();
        String actual = selectCommand.toString();
        assertEquals(expected, actual);
    }

}
