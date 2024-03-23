package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unassigns all tasks assigned to an existing person in the address book.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleartask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns all tasks assigned to the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Tasks for %1$s has been cleared.";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to edit the remark
     */
    public ClearTaskCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    private Person getPersonToBeUnassigned(Model model) throws CommandException {
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return lastShownPersonList.get(index.getZeroBased());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Person personToBeUnassigned = getPersonToBeUnassigned(model);

        Person unassignedPerson = new Person(personToBeUnassigned.getName(), personToBeUnassigned.getPhone(),
                personToBeUnassigned.getEmail(), personToBeUnassigned.getAddress(), new HashSet<>());

        model.setPerson(personToBeUnassigned, unassignedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, unassignedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearTaskCommand)) {
            return false;
        }

        ClearTaskCommand e = (ClearTaskCommand) other;
        return index.equals(e.index);
    }
}
