package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed name from the contact list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the person identified by the "
            + "name used in the contact list.\n" + "Parameters: CONTACT_NAME\n"
            + "Example: " + COMMAND_WORD + " Alex Tan";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Okay, %1$s's contact has been deleted.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Oops, %1$s's contact does not exist.";
    private final String targetName;
    public DeleteCommand(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetName() {
        return targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> contactList = model.getFilteredPersonList();
        Person personToDelete = null;
        for (Person person : contactList) {
            if (person.getName().fullName.equalsIgnoreCase(targetName)) {
                personToDelete = person;
                break;
            }
        }
        if (personToDelete == null) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, targetName));
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, targetName));
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
        return targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetName", targetName).toString();
    }

}
