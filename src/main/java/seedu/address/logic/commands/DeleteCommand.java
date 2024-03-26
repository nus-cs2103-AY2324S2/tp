package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.ui.ConfirmationBox;
import seedu.address.ui.Prompt;


/**
 * Deletes a person identified using the email id from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "-";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by his email id used in the displayed person list.\n"
            + "Parameters: email id, which can include alphabets, numbers, and certain special characters.\n"
            + "Example: " + COMMAND_WORD + " /id johndoe46";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_ID_NOT_FOUND = "User ID %1$s not found. "
           + "Consider checking database to ensure the correct ID has been entered.";
    public static final String MESSAGE_DELETION_CANCELLED = "Deletion cancelled by user.";

    private final Id targetId;

    private Prompt confirmationBox;

    /**
     * Creates a {@code DeleteCommand} with the specified target ID and a custom {@code Prompt}
     * for displaying confirmation dialogs. This constructor is primarily intended for use in
     * scenarios where dependency injection is desired, such as unit testing, allowing for
     * the replacement of the confirmation dialog with a fake implementation.
     *
     * @param targetId The ID of the person to be deleted.
     * @param confirmationBox The {@code Prompt} implementation to be used for displaying
     *                        confirmation dialogs.
     */
    public DeleteCommand(Id targetId, Prompt confirmationBox) {
        this.targetId = targetId;
        this.confirmationBox = confirmationBox;
    }

    /**
     * Creates a {@code DeleteCommand} with the specified target ID and no custom {@code Prompt}.
     * This constructor is intended for normal use, where the default confirmation mechanism
     * is used.
     *
     * @param targetId The ID of the person to be deleted. This is the only parameter required
     *                 to identify the person within the address book for deletion.
     */
    public DeleteCommand(Id targetId) {
        this.targetId = targetId;
        this.confirmationBox = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        boolean isAnyRecordDeleted = false;
        String deletedInformation = "";
        for (Person person : lastShownList) {
            if (person.getId().equals(this.targetId)) {
                boolean confirmation;
                if (confirmationBox == null) {
                    confirmation = new ConfirmationBox().display("Confirmation",
                            "Are you sure you want to delete this person?");
                } else {
                    // This branch is designed for tests only.
                    // In actual use this branch will not be visited.
                    confirmation = confirmationBox.display("test", "test");
                }
                if (!confirmation) {
                    throw new CommandException(MESSAGE_DELETION_CANCELLED);
                }
                assert confirmation;

                Person personToDelete = person;
                model.deletePerson(personToDelete);
                isAnyRecordDeleted = true;
                deletedInformation = Messages.format(personToDelete);
                break;
            }
        }

        if (!isAnyRecordDeleted) {
            throw new CommandException(String.format(MESSAGE_ID_NOT_FOUND, this.targetId.toString()));
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, deletedInformation));
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
        return targetId.equals(otherDeleteCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetId", targetId)
                .toString();
    }
}
