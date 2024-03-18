package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddAppCommand;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class for Appointment..
 */
public class AppointmentUtil {

    /**
     * Returns an addApp command string for adding the {@code appointment}.
     */
    public static String getAddAppCommand(Appointment appointment) {
        return AddAppCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NRIC + appointment.getNric().value + " ");
        sb.append(PREFIX_DATE + appointment.getDate().toString() + " ");
        sb.append(PREFIX_START_TIME + appointment.getTimePeriod().getStartTime().toString() + " ");
        sb.append(PREFIX_END_TIME + appointment.getTimePeriod().getEndTime().toString() + " ");
        sb.append(PREFIX_TAG + appointment.getAppointmentType().typeName + " ");
        sb.append(PREFIX_NOTE + appointment.getNote().note + " ");
        return sb.toString();
    }
}
