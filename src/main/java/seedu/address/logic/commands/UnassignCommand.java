package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Unassigns an existing task to an existing person in the address book.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from unassign");
    }
}
