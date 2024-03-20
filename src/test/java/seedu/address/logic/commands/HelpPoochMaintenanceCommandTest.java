package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpPoochMaintenanceCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_poochMaintenanceHelp_success() {
        CommandResult expectedCommandResult =
                new CommandResult(HelpMessages.MESSAGES_SHOWING_ADD_MAINTAINER_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpPoochMaintenanceCommand(), model, expectedCommandResult, expectedModel);
    }
}
