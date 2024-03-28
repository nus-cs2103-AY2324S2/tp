package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;


/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "/delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the id number provided.\n"
            + "Parameters: ID (must be a positive 6-digit number)\n"
            + "Example: " + COMMAND_WORD + " 240001";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Id id;

    /**
     * Creates a DeleteCommand to delete the worker by specific ID.
     *
     * @param id ID of the worker to be deleted.
     */
    public DeleteCommand(Id id) {
        requireNonNull(id);
        this.id = id;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToDelete = lastShownList
                .stream()
                .filter(person -> person.getId().value == (this.id.value))
                .findFirst()
                .orElse(null);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return id.equals(otherDeleteCommand.id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", id)
                .toString();
    }
}
