package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.InternshipModel.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Task;
import seedu.address.model.internship.TaskList;

/**
 * Edits the details of an existing internship in the internship data.
 */
public class InternshipAddTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a task to the internship identified "
            + "by the index number used in the displayed internship data. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TASK + " TASK "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK + " edit resume";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "Task Added: %1$s";

    public static final String MESSAGE_EMPTY_TASK = "Task cannot be blank!";

    private final Index index;
    private final Task task;

    /**
     * @param index of the internship in the filtered internship list to edit
     * @param task details to edit the internship with
     */
    public InternshipAddTaskCommand(Index index, Task task) {
        requireNonNull(index);
        requireNonNull(task);

        this.index = index;
        this.task = task;
    }

    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (index.getOneBased() > lastShownList.size()) {
            throw new CommandException(InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToAddTask = lastShownList.get(index.getZeroBased());
        Internship internshipWithTask = createInternshipWithTask(internshipToAddTask, task);

        model.setInternship(internshipToAddTask, internshipWithTask);

        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS,
                this.task));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipAddTaskCommand)) {
            return false;
        }

        InternshipAddTaskCommand otherEditCommand = (InternshipAddTaskCommand) other;
        return index.equals(otherEditCommand.index)
                && task.equals(otherEditCommand.task);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("task", task)
                .toString();
    }

    /**
     * Creates and returns a {@code Internship} with the details of {@code internshipToAddTask}
     * edited with {@code internshipWithTaskDescriptor}.
     */
    private static Internship createInternshipWithTask(Internship internshipToAddTask, Task task) {
        assert internshipToAddTask != null;
        TaskList newTaskList = internshipToAddTask.getTaskList().copy();
        newTaskList.addTask(task);

        return new Internship(internshipToAddTask.getCompanyName(), internshipToAddTask.getContactName(),
                internshipToAddTask.getContactEmail(), internshipToAddTask.getContactNumber(),
                internshipToAddTask.getLocation(), internshipToAddTask.getApplicationStatus(),
                internshipToAddTask.getDescription(), internshipToAddTask.getRole(),
                internshipToAddTask.getRemark(),
                newTaskList);
    }
}
