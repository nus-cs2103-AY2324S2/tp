package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTORNRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENTNRIC;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddAppointmentCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DOCTORNRIC + appointment.getDoctoNric().nric + " ");
        sb.append(PREFIX_PATIENTNRIC + appointment.getPatientNric().nric + " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        sb.append(PREFIX_DATE + appointment.getAppointmentDate().appointmentDate.format(formatter) + " ");
        return sb.toString();
    }
}
