package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("NRIC: ")
                .append(person.getNric())
                .append("; Name: ")
                .append(person.getName())
                .append("; DoB: ")
                .append(person.getDoB())
                .append("; Phone: ")
                .append(person.getPhone());
        return builder.toString();
    }

    /**
     * Formats appointment for display in result box
     * @param appointment the appointment in question
     * @return String formatted string as per requirements
     */
    public static String format(Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(appointment.getAppointmentDate())
                .append("; Doctor: ")
                .append(appointment.getDoctoNric())
                .append("; Patient: ")
                .append(appointment.getPatientNric())
                .append("; ID: ")
                .append(appointment.getAppointmentId());
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Doctor doctor) {
        final StringBuilder builder = new StringBuilder();
        builder.append("; NRIC: ")
                .append(doctor.getNric())
                .append("; Name: ")
                .append(doctor.getName())
                .append("; DoB: ")
                .append(doctor.getDoB())
                .append("; Phone: ")
                .append(doctor.getPhone())
                .append(";");
        return builder.toString();
    }

}
