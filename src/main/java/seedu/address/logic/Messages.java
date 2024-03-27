package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_MISSING_UUID = "Missing UUID.";
    public static final String MESSAGE_NO_SUCH_ATTRIBUTE = "No such attribute(s) found: ";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_UUID = "The person UUID provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_PERSON_UUID = "The UUID provided is invalid: ";
    public static final String MESSAGE_MISSING_ATTRIBUTES = "Missing attributes to delete.";
    public static final String MESSAGE_SPACES_IN_UUID = "Spaces found in UUID: ";

    public static final String MESSAGE_INVALID_PREDEFINED_RELATIONSHIP_DESCRIPTOR = "You cannot delete this "
            + "relationship type.";

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
        builder.append("Details:\n")
                .append(person.allAttributesAsString());
        return builder.toString();
    }

}

