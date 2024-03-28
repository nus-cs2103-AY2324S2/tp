package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_ID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Adds an appointment to CogniCare's appointment list.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to CogniCare. "
            + "Parameters: "
            + PREFIX_PATIENT_ID + "PATIENT_ID "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_ATTEND + "ATTEND "
            + PREFIX_FEEDBACK_SCORE + "FEEDBACK_SCORE "
            + PREFIX_APPOINTMENT_DESCRIPTION + "APPOINTMENT DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATIENT_ID + "1 "
            + PREFIX_DATETIME + "2024-03-18 09:00 "
            + PREFIX_ATTEND + "false "
            + PREFIX_FEEDBACK_SCORE + "5 "
            + PREFIX_APPOINTMENT_DESCRIPTION + "This is a new appointment";


    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "This appointment already exists in the appointment list";

    private final Appointment toAdd;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
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

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatAppointment(toAdd)));
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
        return toAdd.equals(otherAddAppointmentCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
