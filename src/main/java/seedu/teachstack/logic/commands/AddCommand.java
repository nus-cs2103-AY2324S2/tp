package seedu.teachstack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_STUDENTID;

import seedu.teachstack.commons.util.ToStringBuilder;
import seedu.teachstack.logic.Messages;
import seedu.teachstack.logic.commands.exceptions.CommandException;
import seedu.teachstack.model.Model;
import seedu.teachstack.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_STUDENTID + "STUDENTID "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GRADE + "GRADE"
            + "[" + PREFIX_GROUP + "GROUP]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_STUDENTID + "A0123456H "
            + PREFIX_EMAIL + "e0456789@u.nus.edu "
            + PREFIX_GRADE + "A+ "
            + PREFIX_GROUP + "Group 1 "
            + PREFIX_GROUP + "Group 1B ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book. "
            + "Check to make sure StudentID and email are unique.";

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
