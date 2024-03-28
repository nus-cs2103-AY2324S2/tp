package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute_helpForSpecificCommand_add() {
        // Set up
        String specificCommand = "add"; // Example specific command "add"
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(AddCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForEditCommand_success() {
        // Set up
        String specificCommand = "edit";
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(EditCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForDeleteCommand_success() {
        // Set up
        String specificCommand = "delete";
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(DeleteCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForClearCommand_success() {
        // Set up
        String specificCommand = "clear";
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(ClearCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForFindCommand_success() {
        // Set up
        String specificCommand = "find";
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(FindCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForListCommand_success() {
        // Set up
        String specificCommand = "list";
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(ListCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_helpForExitCommand_success() {
        // Set up
        String specificCommand = "exit";
        HelpCommand helpCommand = new HelpCommand(specificCommand);

        // Execute
        CommandResult commandResult = helpCommand.execute(model);

        // Verify
        assertEquals(ExitCommand.MESSAGE_USAGE, commandResult.getFeedbackToUser());
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

    @Test
    public void getCommandWord() {
        HelpCommand helpCommand = new HelpCommand("");
        assertEquals(HelpCommand.COMMAND_WORD, helpCommand.getCommandWord());
    }

    @Test
    public void getMessageUsage() {
        HelpCommand helpCommand = new HelpCommand("");
        assertEquals(HelpCommand.MESSAGE_USAGE, helpCommand.getMessageUsage());
    }
}
