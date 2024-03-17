package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the phone number used in the displayed person list.\n"
            + "Parameters: Phone\n"
            + "Example: " + COMMAND_WORD + " 87652533";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Phone targetPhone;

    public DeleteCommand(Phone targetPhone) {
        this.targetPhone = targetPhone;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        boolean isFound = false;
        Person personToDelete = null;

        for (Person p : lastShownList) {
            if (p.getPhone().equals(targetPhone)) {
                personToDelete = p;
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_IN_LIST);
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
        return targetPhone.equals(otherDeleteCommand.targetPhone);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetPhone", targetPhone)
                .toString();
    }
}
