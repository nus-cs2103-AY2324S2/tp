package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.coursemate.CourseMate;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX = "The courseMate index provided is invalid";
    public static final String MESSAGE_COURSE_MATES_LISTED_OVERVIEW = "%1$d course mates listed!";
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
     * Formats the {@code courseMate} for display to the user.
     */
    public static String format(CourseMate courseMate) {
        final StringBuilder builder = new StringBuilder();
        builder.append(courseMate.getName())
                .append("; Phone: ")
                .append(courseMate.getPhone())
                .append("; Email: ")
                .append(courseMate.getEmail())
                .append("; Address: ")
                .append(courseMate.getAddress())
                .append("; Skills: ");
        courseMate.getSkills().forEach(builder::append);
        return builder.toString();
    }

}
