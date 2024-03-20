package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Assign Task to Employees
 */
public class AssignTaskCommand extends Command {

    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assigned task %1$s to %2$s successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a task to a person identified by index number.\n"
            + "Parameters: "
            + PREFIX_TASK + "TASK "
            + PREFIX_DEADLINE + "dd-MM-yyyy HHmm "
            + PREFIX_TO + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK + "Complete Project Proposal "
            + PREFIX_DEADLINE + "22-04-2024 2359 "
            + PREFIX_TO + "1";

    public static final String MESSAGE_PERSON_IS_BUSY = "A task has already been assigned for %1$s. "
            + "Please select another person.";
    private final Index index;
    private final Task task;

    /**
     * Assign task to a existing person in the address book.
     * @param task
     * @param index
     */
    public AssignTaskCommand(Task task, Index index) {
        requireNonNull(index);
        this.task = task;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if (index.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person assignedTo = personList.get(index.getZeroBased());

        if (assignedTo.isBusy()) {
            throw new CommandException(String.format(MESSAGE_PERSON_IS_BUSY, Messages.printName(assignedTo)));
        }

        model.assignTask(task, assignedTo);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS,
                Messages.printTask(task), Messages.printName(assignedTo)));
    }
}
