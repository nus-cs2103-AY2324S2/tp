package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANKDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIRSTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAYRATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
        + "Parameters: "
        + PREFIX_FIRSTNAME + "FIRST NAME "
        + PREFIX_LASTNAME + "LAST NAME "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_SEX + "SEX "
        + PREFIX_PAYRATE + "PAY RATE "
        + PREFIX_ADDRESS + "ADDRESS "
        + PREFIX_BANKDETAILS + "BANK DETAILS \n"
        //  + PREFIX_TAG + "TAG...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_FIRSTNAME + "John "
        + PREFIX_LASTNAME + "Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_SEX + "m "
        + PREFIX_PAYRATE + "14 "
        + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + PREFIX_BANKDETAILS + "0495858505";
    //  + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This phone number already exists in the address book. "
        + "Every contact must have a unique phone number.";
    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
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
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("toAdd", toAdd)
            .toString();
    }
}
