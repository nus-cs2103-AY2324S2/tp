package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their unique ID in the displayed person list.\n"
            + "Parameters: Their unique ID, (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 000001";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_PERSON_NOT_FOUND = "This person does not exist in the address book";

    private final String targetUniqueId;

    /**
     * Creates a DeleteCommand to delete the person with the specified unique ID.
     */
    public DeleteCommand(String targetUniqueId) {
        requireNonNull(targetUniqueId);
        this.targetUniqueId = targetUniqueId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToDelete = model.getPersonByUniqueId(targetUniqueId);

        if (personToDelete == null) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetUniqueId.equals(otherDeleteCommand.targetUniqueId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetUniqueId", targetUniqueId)
                .toString();
    }
}
