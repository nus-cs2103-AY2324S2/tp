package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListInterviewsCommand extends Command {

    public static final String COMMAND_WORD = "list_interviews";

    public static final String MESSAGE_SUCCESS = "Listed all interviews";


    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
