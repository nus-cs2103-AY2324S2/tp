package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Staff;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a person to the address book.
 */
public class AddStaffCommand extends Command {

    public static final String COMMAND_WORD = "/pooch-staff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SALARY + "SALARY "
            + PREFIX_EMPLOYMENT + "EMPLOYMENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe staff "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SALARY + "$50/hr "
            + PREFIX_EMPLOYMENT + "part-time";

    public static final String MESSAGE_SUCCESS = "New staff is added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person's name already exists in the address book";

    private final Staff toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddStaffCommand(Staff person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStaffCommand)) {
            return false;
        }

        AddStaffCommand otherAddCommand = (AddStaffCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
