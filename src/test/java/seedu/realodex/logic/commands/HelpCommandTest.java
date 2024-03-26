package seedu.realodex.logic.commands;

import static seedu.realodex.logic.commands.AddCommand.MESSAGE_ADD_HELP;
import static seedu.realodex.logic.commands.ClearCommand.MESSAGE_CLEAR_HELP;
import static seedu.realodex.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_DELETE_HELP;
import static seedu.realodex.logic.commands.EditCommand.MESSAGE_EDIT_HELP;
import static seedu.realodex.logic.commands.FilterCommand.MESSAGE_FILTER_HELP;
import static seedu.realodex.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.realodex.logic.commands.ListCommand.MESSAGE_LIST_HELP;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.Model;
import seedu.realodex.model.ModelManager;
import seedu.realodex.model.UserPrefs;

public class HelpCommandTest {
    private Model model = new ModelManager(getTypicalRealodex(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRealodex(), new UserPrefs());

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(""), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_commandhelp_success() {
        CommandResult expectedAddHelpCommandResult = new CommandResult(MESSAGE_ADD_HELP,
                false, false);
        assertCommandSuccess(new HelpCommand("add"), model, expectedAddHelpCommandResult, expectedModel);

        CommandResult expectedClearHelpCommandResult = new CommandResult(MESSAGE_CLEAR_HELP,
                false, false);
        assertCommandSuccess(new HelpCommand("clear"), model, expectedClearHelpCommandResult, expectedModel);

        CommandResult expectedDeleteHelpCommandResult = new CommandResult(MESSAGE_DELETE_HELP,
                false, false);
        assertCommandSuccess(new HelpCommand("delete"), model, expectedDeleteHelpCommandResult, expectedModel);

        CommandResult expectedEditHelpCommandResult = new CommandResult(MESSAGE_EDIT_HELP,
                false, false);
        assertCommandSuccess(new HelpCommand("edit"), model, expectedEditHelpCommandResult, expectedModel);

        CommandResult expectedFilterHelpCommandResult = new CommandResult(MESSAGE_FILTER_HELP,
                false, false);
        assertCommandSuccess(new HelpCommand("filter"), model, expectedFilterHelpCommandResult, expectedModel);

        CommandResult expectedListHelpCommandResult = new CommandResult(MESSAGE_LIST_HELP,
                false, false);
        assertCommandSuccess(new HelpCommand("list"), model, expectedListHelpCommandResult, expectedModel);

    }
}
