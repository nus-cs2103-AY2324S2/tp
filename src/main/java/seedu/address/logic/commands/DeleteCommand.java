package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a 5 digit student ID)\n"
            + "Example: " + COMMAND_WORD + " 12345";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final StudentId targetId;

    public DeleteCommand(StudentId targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        boolean found = false;

        // Iterate through the list to find the person with the target student ID
        for (Person candidate : lastShownList) {
            if (candidate.getStudentId().equals(targetId)) {
                // If the person with the target student ID is found, delete it
                model.deletePerson(candidate);
                found = true;
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(candidate)));
            }
        }

        // If the person with the target student ID is not found, throw an exception
        if (!found) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_ID);
        }

        return new CommandResult(MESSAGE_DELETE_PERSON_SUCCESS);
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
