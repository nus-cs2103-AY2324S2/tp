package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Overwrites an existing identity i.e. person to the address book.
 */
public class OverwriteCommand extends Command {

    public static final String COMMAND_WORD = "overwrite";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "Existing person overwritten: %1$s";

    private static final String MESSAGE_NO_EXISTING_PERSON = "The person you provided does not exist in your "
            + "address book. Please try again with another person.";
    private final Person toAdd;

    private int indexOfTarget;

    /**
     * Creates an OverwriteCommand to add the specified {@code Person}
     */
    public OverwriteCommand(Person person, int indexOfTarget) {
        requireNonNull(person);
        toAdd = person;
        this.indexOfTarget = indexOfTarget;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_NO_EXISTING_PERSON);
        }

        Person target = model.getPerson(this.indexOfTarget - 1);
        model.setDuplicatePerson(target, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OverwriteCommand)) {
            return false;
        }

        OverwriteCommand otherOverwriteCommand = (OverwriteCommand) other;
        return toAdd.equals(otherOverwriteCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
