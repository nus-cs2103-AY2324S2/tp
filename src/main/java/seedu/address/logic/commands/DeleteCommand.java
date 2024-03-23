package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed identity card number from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the identity card number used in the displayed person list.\n"
            + "Parameters: IC (must be a valid identity card number)\n"
            + "Example: " + COMMAND_WORD + " S1234567A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: \n"
            + "Name: %1$s\n"
            + "Phone: %2$s\n"
            + "Email: %3$s\n"
            + "Identity Card Number: %4$s\n"
            + "Age: %5$s\n"
            + "Sex: %6$s\n"
            + "Address: %7$s\n";;

    private final IdentityCardNumberMatchesPredicate predicate;

    public DeleteCommand(IdentityCardNumberMatchesPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> allPatients = model.getAddressBook().getPersonList();

        Person personToDelete = allPatients.stream()
                .filter(predicate::test)
                .findFirst()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_NO_MATCHING_IC));

        model.deletePerson(personToDelete);
        String successMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName(),
                personToDelete.getPhone(),
                personToDelete.getEmail(),
                personToDelete.getIdentityCardNumber(),
                personToDelete.getAge(),
                personToDelete.getSex(),
                personToDelete.getAddress());

        return new CommandResult(successMessage);
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
        return predicate.equals(otherDeleteCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
