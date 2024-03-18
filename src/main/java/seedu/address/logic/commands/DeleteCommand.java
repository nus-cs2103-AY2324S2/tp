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

    public static final String COMMAND_WORD = "/remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the person identified by the id number provided.\n"
            + "Parameters: ID (must be a positive 6-digit number)\n"
            + "Example: " + COMMAND_WORD + " 240001";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Removed Person: %1$s";

    private final Id ID;

    public DeleteCommand(Id ID) {
        requireNonNull(ID);
        this.ID = ID;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        Person personToRemove = lastShownList
                .stream()
                .filter(person -> person.getId().value == (this.ID.value))
                .findFirst()
                .orElse(null);

        if (personToRemove == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID);
        }

        model.deletePerson(personToRemove);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToRemove)));
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
        return ID.equals(otherDeleteCommand.ID);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", ID)
                .toString();
    }
}
