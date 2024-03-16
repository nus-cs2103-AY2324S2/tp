package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Assigns an existing task to an existing person in the address book.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from assign");
    }
}
