package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a seller to the address book.
 */
public class AddSellerCommand extends Command {

    public static final String COMMAND_WORD = "addseller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a seller to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_POSTALCODE + "POSTAL CODE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_POSTALCODE + "578578 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New seller added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This seller already exists in the address book";

    private final Person sellerToAdd;

    /**
     * Creates an AddSellerCommand to add the specified seller.
     * @param person The seller to be added.
     */
    public AddSellerCommand(Person person) {
        requireNonNull(person);
        sellerToAdd = person;
    }
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(sellerToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(sellerToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(sellerToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSellerCommand)) {
            return false;
        }

        AddSellerCommand otherAddCommand = (AddSellerCommand) other;
        return sellerToAdd.equals(otherAddCommand.sellerToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sellerToAdd", sellerToAdd)
                .toString();
    }
}
