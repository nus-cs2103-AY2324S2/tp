package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ToggleDisplayCommand.MESSAGE_TOGGLE_ACKNOWLEDGEMENT;

public class ToggleDisplayCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_toggleDisplay_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_TOGGLE_ACKNOWLEDGEMENT, false, false, true);
        assertCommandSuccess(new ToggleDisplayCommand(), model, expectedCommandResult, expectedModel);
    }
}
