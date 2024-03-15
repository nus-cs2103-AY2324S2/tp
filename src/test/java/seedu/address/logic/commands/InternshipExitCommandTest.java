package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipExitCommand.
 */
public class InternshipExitCommandTest {
    private InternshipModel model = new InternshipModelManager();
    private InternshipModel expectedModel = new InternshipModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new InternshipExitCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_commandWordIsExit_success() {
        assertEquals(InternshipExitCommand.COMMAND_WORD, "exit");
    }
}
