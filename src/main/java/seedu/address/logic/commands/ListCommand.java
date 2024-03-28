package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;

import seedu.address.model.Model;

/**
 * Lists all course mates in the contact list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all course mates";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
