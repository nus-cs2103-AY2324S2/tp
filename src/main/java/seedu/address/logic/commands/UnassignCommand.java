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
 * Unassigns an existing task to an existing person in the address book.
 */
public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns the task identified "
            + "by the index number used in the last task listing "
            + "to the person identified "
            + "by the index number used in the last person listing. "
            + "Does nothing if the task not assigned to the person.\n"
            + "Parameters: TASK_INDEX (must be a positive integer) "
            + "to/ [PERSON_INDEX (must be a positive integer)]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "to/ 2";

    public static final String MESSAGE_SUCCESS = "%1$s has been unassigned to %2$s.";

    private final Index taskIndex;
    private final Index personIndex;

    /**
     * @param taskIndex of the task in the filtered task list to be unassigned to the person
     * @param personIndex of the person in the filtered person list to be unassigned the task
     */
    public UnassignCommand(Index taskIndex, Index personIndex) {
        requireAllNonNull(taskIndex, personIndex);

        this.taskIndex = taskIndex;
        this.personIndex = personIndex;
    }

    private Task getTaskToUnassign(Model model) throws CommandException {
        // TODO: change to use filtered list instead
        List<Task> lastShownTaskList = model.getTaskList().getSerializeTaskList();

        if (taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        return lastShownTaskList.get(taskIndex.getZeroBased());
    }

    private Person getPersonToBeUnassigned(Model model) throws CommandException {
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownPersonList.get(personIndex.getZeroBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Task taskToUnassign = getTaskToUnassign(model);
        Person personToBeUnassigned = getPersonToBeUnassigned(model);

        Person unassignedPerson = personToBeUnassigned.deleteTask(taskToUnassign);

        model.setPerson(personToBeUnassigned, unassignedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatTask(taskToUnassign),
                unassignedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnassignCommand)) {
            return false;
        }

        UnassignCommand e = (UnassignCommand) other;
        return taskIndex.equals(e.taskIndex)
                && personIndex.equals(e.personIndex);
    }
}
