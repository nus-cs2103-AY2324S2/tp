package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Id;

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
    public static final String MESSAGE_ID_NOT_FOUND = "User ID %1$s not found. Consider checking database to ensure the correct ID has been entered.";

    private final Id targetId;

    public DeleteCommand(Id targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        boolean isAnyRecordDeleted = false;
        String deletedInformation = "";
        for(Person person : lastShownList) {
            if (person.getId().equals(this.targetId)) {
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
