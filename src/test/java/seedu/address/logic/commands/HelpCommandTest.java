package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_helpForSpecificCommand_success() {
        // Set up
        String specificCommand = "add"; // Example specific command "add"
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(AddCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForAllCommands_success() {
        // Set up
        String specificCommand = ""; // No specific command
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify that the feedback contains usage instructions for all commands (when
        // no specific command is specified)
        // NOTE: When adding a new command, you must add an entry to the commandUsageMap
        // in HelpCommand
        assertEquals(true, commandResult.getFeedbackToUser().contains(AddCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(AddCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(EditCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(EditCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(ClearCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(ClearCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(DeleteCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(DeleteCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(FindCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(FindCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(HelpCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(HelpCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(ListCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(ListCommand.MESSAGE_USAGE));
        assertEquals(true, commandResult.getFeedbackToUser().contains(ExitCommand.COMMAND_WORD));
        assertEquals(true, commandResult.getFeedbackToUser().contains(ExitCommand.MESSAGE_USAGE));
    }
}
