package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.Model;
import scrolls.elder.model.person.Person;

/**
 * Deletes a person identified using its displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_DEL = "del";
    public static final String COMMAND_WORD_RM = "rm";
    public static final String COMMAND_WORD_REMOVE = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD_DELETE
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD_DELETE + " 1"
            + "Alternatively, you can also delete a person using the following commands as well.\n"
            + "Example: " + COMMAND_WORD_DEL + " 1"
            + "Example: " + COMMAND_WORD_RM + " 1"
            + "Example: " + COMMAND_WORD_REMOVE + " 1";



    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_ERROR = "Unable to delete contact: ";
    public static final String MESSAGE_CONFIRM_DELETE =
            "Valid contact inputted. Are you sure you want to delete this contact?";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_DELETE_PERSON_ERROR + Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

        //Check if the person to be deleted is paired with another person.
        if (personToDelete.isPairPresent(personToDelete)) {
            throw new CommandException(MESSAGE_DELETE_PERSON_ERROR + Messages.MESSAGE_CONTACT_PAIRED_BEFORE_DELETE);
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
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
