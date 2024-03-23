package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_generalHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("general"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("delete"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_searchHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_SEARCH_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("search"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_EDIT_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("edit"), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_addHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpMessages.MESSAGES_SHOWING_ADD_HELP_MESSAGE,
                true, false);
        assertCommandSuccess(new HelpCommand("add"), model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_invalidCommandType_throwsCommandException() {
        String invalidCommand = "poodles";
        HelpCommand helpCommand = new HelpCommand(invalidCommand);

        assertCommandFailure(helpCommand, model, HelpMessages.MESSAGES_INVALID_COMMAND_TYPE);
    }
}
