package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.testutil.TypicalEvents;

public class DeselectCommandTest {

    @Test
    public void execute_deselectSuccessful() throws CommandException {
        ModelManager model = new ModelManager();
        model.setEventBook(TypicalEvents.getTypicalEventBook());
        model.selectEvent(TypicalEvents.BINGO);
        DeselectCommand deselectCommand = new DeselectCommand();
        CommandResult commandResult = deselectCommand.execute(model);
        assertEquals(DeselectCommand.MESSAGE_DESELECT_EVENT_SUCCESS, commandResult.getFeedbackToUser());
    }

    @Test
    public void toStringMethod() {
        DeselectCommand deselectCommand = new DeselectCommand();
        String expected = DeselectCommand.class.getCanonicalName() + "{}";
        assertEquals(expected, deselectCommand.toString());
    }
}
