package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COURSE_MATE_NAME = "The courseMate name provided is not found!";
    public static final String MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX = "The courseMate index provided is invalid";
    public static final String MESSAGE_COURSE_MATES_LISTED_OVERVIEW = "%1$d course mates listed!";
    public static final String MESSAGE_GROUPS_LISTED_OVERVIEW = "%1$d groups listed!";
    public static final String MESSAGE_INVALID_GROUP_NAME = "The group name provided is not found!";
    public static final String MESSAGE_MEMBERS_DONT_EXIST = "Some of the specified members could not be found.";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_SIMILAR_COURSE_MATE_NAME = "There are %1$d course mates with similar names\n"
            + "Retry the command by specifying the index of the contact in the list, example: “#1”.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }
}
