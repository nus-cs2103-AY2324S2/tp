package seedu.findvisor.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.findvisor.logic.parser.Prefix;
import seedu.findvisor.model.person.Meeting;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.Remark;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_FIND_PERSONS_LISTED_OVERVIEW = "Search result for: %1$s. \n%2$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EMPTY_FIELD = "Empty value for field: %1$s!";

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
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code meeting} for display to the user.
     */
    public static String format(Meeting meeting) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Meeting Start: ")
                .append(meeting.getStartString())
                .append("; End: ")
                .append(meeting.getEndString());
        if (!meeting.remark.equals("")) {
            builder.append("\nMeeting Remark: ")
                    .append(meeting.remark);
        }
        return builder.toString();
    }

    /**
     * Formats the {@code remark} for display to the user
     */
    public static String format(Remark remark) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Remark: ").append(remark.value);
        return builder.toString();
    }
}
