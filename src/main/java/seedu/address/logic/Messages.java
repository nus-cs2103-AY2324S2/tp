package seedu.address.logic;

import java.util.ArrayList;
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
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_MISSING_NUSNET =
            "There is no student with this NUSNet ID!";
    public static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Marked Attendance for Person: ";
    public static final String MESSAGE_MARK_EXISTING_ATTENDANCE_SUCCESS =
            "Re-marked Attendance for Person: ";
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

}
