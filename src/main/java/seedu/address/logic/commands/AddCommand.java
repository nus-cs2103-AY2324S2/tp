package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUNDING_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.startup.Startup;

/**
 * Adds a startup to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a startup to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_INDUSTRY + "INDUSTRY "
            + PREFIX_FUNDING_STAGE + "FUNDING STAGE "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Allium "
            + PREFIX_INDUSTRY + "WEB3 "
            + PREFIX_FUNDING_STAGE + "S "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "allium@gmail.com "
            + PREFIX_ADDRESS + "420, 23rd Street, #02-25 "
            + PREFIX_TAG + "competitive "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New startup added: %1$s";
    public static final String MESSAGE_DUPLICATE_STARTUP = "This startup already exists in the address book";

    private final Startup toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Startup}
     */
    public AddCommand(Startup startup) {
        requireNonNull(startup);
        toAdd = startup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStartup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STARTUP);
        }

        model.addStartup(toAdd);
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
