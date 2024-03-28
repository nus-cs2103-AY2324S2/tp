package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "ac";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the TA Toolkit. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CLASS_GROUP + "CLASS/GROUP "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM ID] "
            + "[" + PREFIX_GITHUB + "GITHUB ID]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_CLASS_GROUP + "F14-3 "
            + PREFIX_EMAIL + "johndoe@gmail.com "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_TELEGRAM + "@johndoe "
            + PREFIX_GITHUB + "johnDoeGithub ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_FIELD = "This person contains duplicate fields"
            + "(Email, Phone, Telegram, or Github) with another person.";

    private static final Logger logger = LogsCenter.getLogger(AddCommand.class);
    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
        logger.log(Level.INFO, "AddCommand created with Person: " + person.toString());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FIELD);
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
