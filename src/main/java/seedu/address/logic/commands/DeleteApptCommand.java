package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.core.date.Date;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.appointment.Mark;
import seedu.address.model.appointment.Note;
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.patient.Nric;

/**
 * Deletes an appointment identified using its NRIC, date, start time and end time.
 */
public class DeleteApptCommand extends Command {

    public static final String COMMAND_WORD = "deleteAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the NRIC, date, start time and end time given.\n"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "T0123456A "
            + PREFIX_DATE + "2024-02-20 "
            + PREFIX_START_TIME + "11:00 "
            + PREFIX_END_TIME + "11:30 ";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    private Appointment apptToDelete;
    private final Nric nricToMatch;
    private final Date dateToMatch;
    private final TimePeriod timePeriodToMatch;

    /**
     * Creates a CancelAppCommand to add the specified {@code Nric, Date, TimePeriod}
     */
    public DeleteApptCommand(Nric nricToMatch, Date dateToMatch, TimePeriod timePeriodToMatch) {
        this.nricToMatch = nricToMatch;
        this.dateToMatch = dateToMatch;
        this.timePeriodToMatch = timePeriodToMatch;
        this.apptToDelete = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPatientWithNric(nricToMatch)) {
            throw new CommandException(Messages.MESSAGE_PATIENT_NRIC_NOT_FOUND);
        }

        Appointment mockAppointmentToMatch = new Appointment(nricToMatch, dateToMatch, timePeriodToMatch,
            new AppointmentType("Anything"), new Note("Anything"), new Mark(false));
        if (!model.hasAppointment(mockAppointmentToMatch)) {
            throw new CommandException(Messages.MESSAGE_APPOINTMENT_NOT_FOUND);
        }

        this.apptToDelete = model.getMatchingAppointment(nricToMatch, dateToMatch, timePeriodToMatch);

        model.cancelAppointment(apptToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, Messages.format(apptToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteApptCommand)) {
            return false;
        }

        DeleteApptCommand otherDeleteApptCommand = (DeleteApptCommand) other;

        // Check if all fields are equal except apptToCancel as not initialised until execute
        return nricToMatch.equals(otherDeleteApptCommand.nricToMatch)
                && dateToMatch.equals(otherDeleteApptCommand.dateToMatch)
                && timePeriodToMatch.equals(otherDeleteApptCommand.timePeriodToMatch);
    }

    @Override
    public String toString() {
        // Build based on all fields except apptToCancel as not initialised until execute
        return new ToStringBuilder(this)
                .add("nric", nricToMatch)
                .add("date", dateToMatch)
                .add("timePeriod", timePeriodToMatch)
                .toString();
    }
}
