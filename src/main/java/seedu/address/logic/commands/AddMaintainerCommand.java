package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.AddMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Maintainer;

/**
 * Adds a person to the address book.
 */
public class AddMaintainerCommand extends Command {

    public static final String COMMAND_WORD = "/pooch-maintainer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a maintainer to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_SKILL + "SKILL "
            + PREFIX_COMMISSION + "COMMISSION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe trainer "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_SKILL + "train dog "
            + PREFIX_COMMISSION + "$50/hr";

    private final Maintainer toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddMaintainerCommand(Maintainer person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(AddMessages.MESSAGE_ADD_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(AddMessages.MESSAGE_ADD_PERSON_SUCCESS, AddMessages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMaintainerCommand)) {
            return false;
        }

        AddMaintainerCommand otherAddCommand = (AddMaintainerCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
