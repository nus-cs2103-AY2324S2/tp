package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS_VIEW;

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
import seedu.address.model.person.Nric;

/**
 * Marks an existing appointment in the CLInic as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Mark the appointment of the person identified as completed"
            + "Parameters: "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME";

    public static final String MESSAGE_MARK_PERSON_SUCCESS = "Appointment successfully marked as seen: %1$s";

    private final Nric nric;
    private final Date date;
    private final TimePeriod timePeriod;

    /**
     * @param nric nric of the Appointment to be identified
     * @param date date of the existing Appointment to be specified
     * @param timePeriod timePeriod of the existing Appointment to be marked
     */
    public MarkCommand(Nric nric, Date date, TimePeriod timePeriod) {
        requireNonNull(nric);
        requireNonNull(date);
        requireNonNull(timePeriod);

        this.nric = nric;
        this.date = date;
        this.timePeriod = timePeriod;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Appointment mockAppointmentToMatch = new Appointment(nric, date, timePeriod,
            new AppointmentType("Anything"), new Note("Anything"), new Mark(false));
        if (!model.hasAppointment(mockAppointmentToMatch)) {
            throw new CommandException(Messages.MESSAGE_APPOINTMENT_NOT_FOUND);
        }

        Appointment appt = model.getMatchingAppointment(nric, date, timePeriod);

        Appointment newAppt = new Appointment(appt.getNric(), appt.getDate(), appt.getTimePeriod(),
            appt.getAppointmentType(), appt.getNote(), new Mark(true));

        model.setAppointment(appt, newAppt);

        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS, PREDICATE_SHOW_ALL_APPOINTMENTS_VIEW);
        return new CommandResult(String.format(MESSAGE_MARK_PERSON_SUCCESS, Messages.format(newAppt)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return nric.equals(otherMarkCommand.nric)
                && date.equals(otherMarkCommand.date)
                && timePeriod.equals(otherMarkCommand.timePeriod);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", nric)
                .add("date", date)
                .add("timePeriod", timePeriod)
                .toString();
    }
}
