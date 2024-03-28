package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds an applicant to Tether.
 */
public class AddApplicantPersonCommand extends AddPersonCommand {

    public static final String COMMAND_WORD = AddPersonCommand.COMMAND_WORD + "_applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an applicant to Tether. "
            + AddPersonCommand.MESSAGE_USAGE;


    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This applicant already exists in Tether."
            + " Do ensure phone number is unique";


    /**
     * Creates an AddApplicantCommand to add the specified {@code Person}
     */
    public AddApplicantPersonCommand(Person person) {
        super(person);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd) || model.hasPersonWithSamePhone(toAdd)) {
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
        if (!(other instanceof AddApplicantPersonCommand)) {
            return false;
        }

        AddPersonCommand otherAddPersonCommand = (AddApplicantPersonCommand) other;
        return toAdd.equals(otherAddPersonCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
