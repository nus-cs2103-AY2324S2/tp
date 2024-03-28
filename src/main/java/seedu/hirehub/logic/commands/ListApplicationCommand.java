package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.model.Model.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.hirehub.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListApplicationCommand extends Command {

    public static final String COMMAND_WORD = "list_app";

    public static final String MESSAGE_SUCCESS = "Listed all available applications from the list!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
