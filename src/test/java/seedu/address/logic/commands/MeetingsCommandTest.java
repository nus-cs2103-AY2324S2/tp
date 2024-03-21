package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MeetingsCommand.SHOWING_MEETINGS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class MeetingsCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_meetings_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_MEETINGS_MESSAGE, false, true, false);
        assertCommandSuccess(new MeetingsCommand(), model, expectedCommandResult, expectedModel);
    }
}
