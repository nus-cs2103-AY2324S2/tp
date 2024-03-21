package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListSchedCommand extends Command {

    public static final String COMMAND_WORD = "listSched";

    public static final String MESSAGE_SUCCESS = "Listed all schedules";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
