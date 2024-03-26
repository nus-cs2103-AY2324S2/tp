package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_OWNER;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Mark tasks done by employees
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked %1$s's task %2$s as done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the task assigned to the identified person as done.\n"
            + "Parameters: "
            + PREFIX_TASK + "TASK "
            + PREFIX_TASK_OWNER + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK + "Complete Project Proposal "
            + PREFIX_TASK_OWNER + "1";

    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "%s has not been assigned this Task. "
            + "Please select another Task";
    private final String taskName;
    private final Index index;

    /**
     * Mark task of a existing person in the address book as done.
     * @param taskName
     * @param index
     */
    public MarkTaskCommand(String taskName, Index index) {
        requireAllNonNull(index, taskName);
        this.taskName = taskName;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if (index.getOneBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person taskOwner = personList.get(index.getZeroBased());

        if (!taskOwner.hasTask(taskName)) {
            throw new CommandException(String.format(MESSAGE_TASK_DOES_NOT_EXIST, Messages.printName(taskOwner)));
        }
        Task task = taskOwner.getTask();

        model.markTask(task);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS,
                Messages.printName(taskOwner), Messages.printTask(task)));

    }
}
