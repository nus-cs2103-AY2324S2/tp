package seedu.realodex.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.realodex.model.Model;

/**
 * Lists all persons in realodex to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";
    public static final String MESSAGE_LIST_HELP = "List Command: Lists all clients in Realodex.\n"
            + "Format: list\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
