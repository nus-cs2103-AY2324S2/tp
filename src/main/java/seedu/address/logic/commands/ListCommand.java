package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STARTUPS;

import seedu.address.model.Model;

/**
 * Lists all startup investments in CapitalConnect to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all startup investments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStartupList(PREDICATE_SHOW_ALL_STARTUPS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
