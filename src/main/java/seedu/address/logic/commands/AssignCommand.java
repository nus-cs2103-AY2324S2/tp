package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Assigns an existing task to an existing person in the address book.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the task identified "
            + "by the index number used in the last task listing "
            + "to the person identified "
            + "by the index number used in the last person listing. "
            + "Does nothing if the task is already assigned to the person.\n"
            + "Parameters: TASK_INDEX (must be a positive integer) "
            + "to/ [PERSON_INDEX (must be a positive integer)]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "to/ 2";

    public static final String MESSAGE_SUCCESS = "%1$s has been assigned to %2$s.";

    private final Index taskIndex;
    private final Index personIndex;

    /**
     * @param taskIndex of the task in the filtered task list to be assigned to the person
     * @param personIndex of the person in the filtered person list to be assigned the task
     */
    public AssignCommand(Index taskIndex, Index personIndex) {
        requireAllNonNull(taskIndex, personIndex);

        this.taskIndex = taskIndex;
        this.personIndex = personIndex;
    }

    private Task getTaskToAssign(Model model) throws CommandException {
        // TODO: change to use filtered list instead
        List<Task> lastShownTaskList = model.getTaskList().getSerializeTaskList();

        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        return lastShownTaskList.get(taskIndex.getZeroBased());
    }

    private Person getPersonToBeAssigned(Model model) throws CommandException {
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownPersonList.get(personIndex.getZeroBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Task taskToAssign = getTaskToAssign(model);
        Person personToBeAssigned = getPersonToBeAssigned(model);

        Person assignedPerson = personToBeAssigned.addTask(taskToAssign);

        model.setPerson(personToBeAssigned, assignedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatTask(taskToAssign),
                assignedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        AssignCommand e = (AssignCommand) other;
        return taskIndex.equals(e.taskIndex)
                && personIndex.equals(e.personIndex);
    }
}
