package seedu.edulink.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.edulink.logic.parser.Prefix;
import seedu.edulink.model.student.Student;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student with given ID does not exist!";
    public static final String MESSAGE_NO_HISTORY_FOUND = "No History available to Undo or Max";
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
    public static String format(Student student) {
        final StringBuilder builder = new StringBuilder();
        builder.append(student.getId())
            .append("; Name: ")
            .append(student.getName())
            .append("; Phone: ")
            .append(student.getPhone())
            .append("; Email: ")
            .append(student.getEmail())
            .append("; Address: ")
            .append(student.getAddress())
            .append("; Major: ")
            .append(student.getMajor())
            .append("; Intake: ")
            .append(student.getIntake())
            .append("; Tags: ");
        student.getTags().forEach(builder::append);
        return builder.toString();
    }

}
