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
import seedu.address.model.appointment.TimePeriod;
import seedu.address.model.person.Nric;

/**
 * Cancels an appointment identified using its NRIC, date, start time and end time.
 */
public class CancelAppCommand extends Command {

    public static final String COMMAND_WORD = "cancelApp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Cancels the appointment identified by the NRIC, date, start time and end time given.\n"
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

    public static final String MESSAGE_CANCEL_APPOINTMENT_SUCCESS = "Cancelled Appointment: %1$s";

    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient of given Nric is not found";

    private Appointment apptToCancel;
    private final Nric nricToMatch;
    private final Date dateToMatch;
    private final TimePeriod timePeriodToMatch;

    /**
     * Creates a CancelAppCommand to add the specified {@code Nric, Date, TimePeriod}
     */
    public CancelAppCommand(Nric nricToMatch, Date dateToMatch, TimePeriod timePeriodToMatch) {
        this.nricToMatch = nricToMatch;
        this.dateToMatch = dateToMatch;
        this.timePeriodToMatch  = timePeriodToMatch;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPersonWithNric(nricToMatch)) {
            throw new CommandException(MESSAGE_PATIENT_NOT_FOUND);
        }

        this.apptToCancel = model.getMatchingAppointment(nricToMatch, dateToMatch, timePeriodToMatch);

        model.cancelAppointment(apptToCancel);
        return new CommandResult(String.format(MESSAGE_CANCEL_APPOINTMENT_SUCCESS, Messages.format(apptToCancel)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CancelAppCommand)) {
            return false;
        }

        CancelAppCommand otherCancelAppCommand = (CancelAppCommand) other;
        return apptToCancel.equals(otherCancelAppCommand.apptToCancel);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("appointment", apptToCancel)
                .toString();
    }
}
