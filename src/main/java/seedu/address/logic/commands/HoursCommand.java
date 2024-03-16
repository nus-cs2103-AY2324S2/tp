package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.WorkHours;

/**
 * Adds work hours to a person in the address book.
 */
public class HoursCommand extends Command {

    public static final String COMMAND_WORD = "hours";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds work hours to a person in the address book. "
            + "Parameters: <Phone> <Hours>\n"
            + "Example: " + COMMAND_WORD + " 98765432 5";

    public static final String MESSAGE_SUCCESS = "%1$s has worked %2$d hours this week";
    public static final String MESSAGE_PERSON_NOT_FOUND = "The person with phone number: %1$s does not exist";

    private final Phone phoneNumber;
    private final WorkHours hoursWorked;

    /**
     * Creates an HoursCommand to add work hours to the person with the specified phone number.
     */
    public HoursCommand(Phone phoneNumber, WorkHours hoursWorked) {
        requireNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToUpdate = model.getPersonByPhoneNumber(phoneNumber);
        if (personToUpdate == null) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND, phoneNumber));
        }

        personToUpdate.setHoursWorked(hoursWorked);
        model.updatePerson(personToUpdate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToUpdate.getName(), hoursWorked.getHoursWorked()));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HoursCommand)) {
            return false;
        }

        HoursCommand otherCommand = (HoursCommand) other;
        return phoneNumber.equals(otherCommand.phoneNumber)
                && hoursWorked == otherCommand.hoursWorked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("phoneNumber", phoneNumber)
                .add("hoursWorked", hoursWorked)
                .toString();
    }
}
