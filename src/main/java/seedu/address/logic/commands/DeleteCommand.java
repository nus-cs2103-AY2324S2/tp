package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted %1$s People";
    public static final String COMMAND_WORD = "delete";

    public static final Object MESSAGE_USAGE = "delete <index> <index>...\n"
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + "delete" + " 1";
    private final List<Index> targetIndices;

    public DeleteCommand(List<Index> targetIndices) {
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        List<Person> personsToDelete = new ArrayList<>();
        for (Index index : targetIndices) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(index.getZeroBased());
            personsToDelete.add(personToDelete);
        }

        for (Person personToDelete : personsToDelete) {
            model.deletePerson(personToDelete);
        }

        model.commitAddressBook();
        return new CommandResult(getSuccessMessage(personsToDelete));
    }

    public String getSuccessMessage(List<Person> deletedPersons) {
        StringBuilder message = new StringBuilder(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedPersons.size()));
        for (Person person : deletedPersons) {
            message.append("\nDeleted Person: ").append(person.getName()).append("\n").append(person);
        }
        return message.toString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{targetIndices=" + targetIndices + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand other = (DeleteCommand) obj;
        return targetIndices.equals(other.targetIndices);
    }


}

