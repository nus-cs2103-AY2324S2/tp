package seedu.internhub.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_INTERN_DURATION;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.internhub.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.internhub.commons.util.ToStringBuilder;
import seedu.internhub.logic.Messages;
import seedu.internhub.logic.commands.exceptions.CommandException;
import seedu.internhub.model.Model;
import seedu.internhub.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + PREFIX_TAG + "TAG "
            + PREFIX_JOB_DESCRIPTION + "JOB DESCRIPTION "
            + "[" + PREFIX_INTERVIEW_DATE + "INTERVIEW DATE] "
            + PREFIX_INTERN_DURATION + "INTERN DURATION "
            + PREFIX_SALARY + "SALARY\n"

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Happy Burger "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "HappyBurger@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "I "
            + PREFIX_JOB_DESCRIPTION + "Software Developer intern "
            + PREFIX_INTERVIEW_DATE + "03-05-2024 1200 "
            + PREFIX_INTERN_DURATION + "3 months "
            + PREFIX_SALARY + "1000";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

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
