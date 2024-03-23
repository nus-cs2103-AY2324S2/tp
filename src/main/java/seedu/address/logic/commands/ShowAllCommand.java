package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Shows all employees in the employee list to the user.
 */
public class ShowAllCommand extends Command {
    public static final String COMMAND_WORD = "showAll";

    @Override
    public CommandResult execute(Model model) {
        model.updateFilteredEmployeeList(employee -> true);
        return new CommandResult("Showing all employees.");
    }
}
