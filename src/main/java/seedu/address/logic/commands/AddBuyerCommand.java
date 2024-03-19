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
 * Adds a buyer to the address book.
 */
public class AddBuyerCommand extends Command {

    public static final String COMMAND_WORD = "addbuyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a buyer to the address book. "
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

    public static final String MESSAGE_SUCCESS = "New buyer added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This buyer already exists in the address book";

    private final Person buyerToAdd;

    /**
     * Creates an AddBuyerCommand to add the specified buyer.
     * @param person The buyer to be added.
     */
    public AddBuyerCommand(Person person) {
        requireNonNull(person);
        buyerToAdd = person;
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

        if (model.hasPerson(buyerToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(buyerToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(buyerToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddBuyerCommand)) {
            return false;
        }

        AddBuyerCommand otherAddCommand = (AddBuyerCommand) other;
        return buyerToAdd.equals(otherAddCommand.buyerToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("buyerToAdd", buyerToAdd)
                .toString();
    }
}
