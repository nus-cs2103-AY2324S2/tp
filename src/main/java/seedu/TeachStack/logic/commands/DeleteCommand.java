package seedu.TeachStack.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.TeachStack.commons.util.ToStringBuilder;
import seedu.TeachStack.logic.Messages;
import seedu.TeachStack.logic.commands.exceptions.CommandException;
import seedu.TeachStack.model.Model;
import seedu.TeachStack.model.person.Person;
import seedu.TeachStack.model.person.StudentId;

/**
 * Deletes a person identified using it's student id from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their student id.\n"
            + "Parameters: STUDENT_ID (must be in format Axxxxxxx[A-Z] where x can be any digit, "
            + "[A-Z] can be any capital letter).\n"
            + "Example: " + COMMAND_WORD + " A0123456A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final StudentId targetId;

    public DeleteCommand(StudentId targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getAddressBook().getPersonList();

        Optional<Person> personOptional = lastShownList.stream()
                .filter(p -> p.getStudentId().equals(targetId))
                .findFirst();

        if (!personOptional.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_STUDENT_ID);
        }

        Person personToDelete = personOptional.get();
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
        return targetId.equals(otherDeleteCommand.targetId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetId", targetId)
                .toString();
    }
}
