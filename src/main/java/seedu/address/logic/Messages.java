package seedu.address.logic;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_MISSING_NUSNET = "No such student with the NUSNET_ID found in contacts!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_MARKED_ATTENDANCE_SUCCESS = "Marked attendance for student: ";
    public static final String MESSAGE_MARK_EXISTING_ATTENDANCE_SUCCESS =
            "Re-marked Attendance for student: ";
    public static final String MESSAGE_UNMARKED_ATTENDANCE_SUCCESS = "Unmarked attendance for student: ";
    public static final String MESSAGE_UNMARK_NONEXISITING_ATTENDANCE_SUCCESS =
            "Attendance is unmarked for student: ";

    private static final String[] ERROR_MESSAGES = {
        MESSAGE_UNKNOWN_COMMAND,
        MESSAGE_INVALID_COMMAND_FORMAT,
        MESSAGE_INVALID_PERSON_DISPLAYED_INDEX,
        MESSAGE_MISSING_NUSNET,
        MESSAGE_DUPLICATE_FIELDS,
    };

    private static final String[] SUCCESS_MESSAGES = {
        MESSAGE_PERSONS_LISTED_OVERVIEW,
        MESSAGE_MARKED_ATTENDANCE_SUCCESS,
        MESSAGE_MARK_EXISTING_ATTENDANCE_SUCCESS,
        MESSAGE_UNMARKED_ATTENDANCE_SUCCESS,
        MESSAGE_UNMARK_NONEXISITING_ATTENDANCE_SUCCESS,
    };

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        ArrayList<String> duplicateFieldsArray = new ArrayList<>(duplicateFields);
        duplicateFieldsArray.sort(String::compareToIgnoreCase);

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFieldsArray);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; NUSNET: ")
                .append(person.getNusNet())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Attendance: ");
        person.getAttendance().forEach(builder::append);
        builder.append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns true if the message is a message representing the failure of a command.
     */
    public static boolean isErrorMessage(String message) {
        requireNonNull(message);
        return Arrays
                .stream(ERROR_MESSAGES)
                .map(Messages::stripMessagePlaceholders)
                .anyMatch(message::contains);
    }

    /**
     * Returns true if the message is a message representing the success of a command.
     */
    public static boolean isSuccessMessage(String message) {
        requireNonNull(message);
        return Arrays
                .stream(SUCCESS_MESSAGES)
                .map(Messages::stripMessagePlaceholders)
                .anyMatch(message::contains);
    }

    /**
     * Strips whitespace and {@code %1$s} placeholders from the message.
     */
    private static String stripMessagePlaceholders(String message) {
        if (message.contains("%1$s")) {
            return String.format(message, "").trim();
        } else {
            return message.trim();
        }
    }
}
