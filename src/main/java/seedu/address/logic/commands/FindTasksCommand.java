package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in TaskMasterPro whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTasksCommand extends Command {

    public static final String COMMAND_WORD = "findtasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks with descriptions containing any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " project";

    private final TaskNameContainsKeywordsPredicate predicate;

    public FindTasksCommand(TaskNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTasksCommand)) {
            return false;
        }

        FindTasksCommand otherFindCommand = (FindTasksCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

