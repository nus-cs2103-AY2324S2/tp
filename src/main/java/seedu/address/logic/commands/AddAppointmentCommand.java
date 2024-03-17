package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTORNRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENTNRIC;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.InvalidAppointmentException;


/**
 * Command to add an appointment to MediCLI
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the MediCLI system. "
            + "Parameters: "
            + PREFIX_DATE + "DATE "
            + PREFIX_DOCTORNRIC + "DOCTOR NRIC "
            + PREFIX_PATIENTNRIC + "PATIENT NRIC "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "2024-04-09 "
            + PREFIX_DOCTORNRIC + "S7777888T "
            + PREFIX_PATIENTNRIC + "T0000111U";

    public static final String MESSAGE_SUCCESS = "New Appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the MediCLI";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        if (!model.isValidAppointment(toAdd)) {
            throw new InvalidAppointmentException();
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
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

        AddAppointmentCommand otherAddCommand = (AddAppointmentCommand) other;
        return toAdd.isSameAppointment(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
