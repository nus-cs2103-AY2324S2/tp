package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;

/**
 * Lists all tasks in TaskMasterPro to the user.
 */
public class ListTasksCommand extends Command {

    public static final String COMMAND_WORD = "listtasks";

    public static final String MESSAGE_SUCCESS = "Listed all tasks";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
