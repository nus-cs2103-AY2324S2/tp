package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.List;
import java.util.UUID;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment to the address book. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_DATE + "17/01/2024 9am-2pm";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the address book";

    private final Index index; // Index of person card to link appointment
    private final AppointmentTime appointmentTime;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Index index, AppointmentTime appointmentTime) {
        requireNonNull(index);
        requireNonNull(appointmentTime);

        this.index = index;
        this.appointmentTime = appointmentTime;
    }

    /**
     * Finds the person object based on index given. Then creates appointment linked to that person.
     * TODO: Ensure that this command is executed ONLY when the current view is PERSON.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddAppointmentFor = lastShownPersonList.get(index.getZeroBased());
        UUID personId = personToAddAppointmentFor.getId();

        Appointment appointmentToAdd = new Appointment(personId, appointmentTime);

        if (model.hasAppointment(appointmentToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(appointmentToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatAppointment(appointmentToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherAddAppointmentCommand = (AddAppointmentCommand) other;
        return index.equals(otherAddAppointmentCommand.index)
                && appointmentTime.equals(otherAddAppointmentCommand.appointmentTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("appointmentTime", appointmentTime)
                .toString();
    }
}
