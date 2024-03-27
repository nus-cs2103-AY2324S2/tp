package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SwitchViewCommand.MESSAGE_SWITCH_VIEW_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SwitchViewCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SWITCH_VIEW_SUCCESS, false, false, true);
        assertCommandSuccess(new SwitchViewCommand(), model, expectedCommandResult, expectedModel);
    }
}
