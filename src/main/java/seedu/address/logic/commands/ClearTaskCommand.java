package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Unassigns all tasks assigned to an existing person in the address book.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleartask";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from cleartask");
    }
}
