package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a person to the address book.
 */
public class AddSupplierCommand extends Command {

    public static final String COMMAND_WORD = "/pooch-supplier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a supplier to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PRODUCT + "PRODUCT "
            + PREFIX_PRICE + "PRICE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe supplier "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_PRODUCT + "poochie food "
            + PREFIX_PRICE + "$50/bag";

    public static final String MESSAGE_SUCCESS = "New supplier is added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This supplier already exists in the address book";

    private final Supplier toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddSupplierCommand(Supplier person) {
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
        if (!(other instanceof AddSupplierCommand)) {
            return false;
        }

        AddSupplierCommand otherAddCommand = (AddSupplierCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
