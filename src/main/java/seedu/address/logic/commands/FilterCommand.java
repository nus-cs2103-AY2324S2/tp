package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Filters list of student based on tags.
 * Can have multiple tags involved.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Filter Command Excuted");
    }
}
