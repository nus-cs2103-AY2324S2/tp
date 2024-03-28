package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipHelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipHelpCommand.
 */
public class InternshipHelpCommandTest {
    private InternshipModel model = new InternshipModelManager();
    private InternshipModel expectedModel = new InternshipModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new InternshipHelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_commandWordIsHelp_success() {
        assertEquals(InternshipHelpCommand.COMMAND_WORD, "help");
    }
}
