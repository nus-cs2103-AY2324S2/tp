package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.util.CommandUtil.generateMessageUsage;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_ADDRESS;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_EMAIL;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_NAME;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_NUSNET;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_PHONE;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "addstu";

    public static final String MESSAGE_USAGE = generateMessageUsage(
            COMMAND_WORD,
            "Adds a person to the address book. ",
            PARAMETER_NAME,
            PARAMETER_PHONE,
            PARAMETER_EMAIL,
            PARAMETER_NUSNET,
            PARAMETER_ADDRESS,
            PARAMETER_TAG.asMultiple(2)
    );

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Person person) {
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
        if (!(other instanceof AddPersonCommand)) {
            return false;
        }

        AddPersonCommand otherAddCommand = (AddPersonCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
