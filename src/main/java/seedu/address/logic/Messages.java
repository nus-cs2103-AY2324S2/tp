package seedu.address.logic;

import static seedu.address.logic.parser.CliSyntax.PREFIX_STAGE;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.applicant.Applicant;
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
    public static final String MESSAGE_INPUT_NOT_REQUIRED_FIELDS = "Input not required for the following field(s): ";
    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        // Compile the list of duplicate prefixes for error message in natural order
        List<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).sorted().collect(Collectors.toList());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Returns an error message indicating not required input prefixes.
     */
    public static String getErrorMessageForInputNotRequiredPrefixes(Prefix... notRequiredPrefixes) {
        assert Arrays.asList(notRequiredPrefixes).contains(PREFIX_STAGE);

        // Compile the list of input not required prefixes for error message in natural order
        List<String> notRequiredFields =
            Stream.of(notRequiredPrefixes).filter(x -> x.equals(PREFIX_STAGE)).map(Prefix::toString)
                .sorted().collect(Collectors.toList());

        return MESSAGE_INPUT_NOT_REQUIRED_FIELDS + String.join(" ", notRequiredFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        return new ToStringBuilder(person)
                .add("name", person.getName())
                .add("phone", person.getPhone())
                .add("email", person.getEmail())
                .add("address", person.getAddress())
                .add("tags", person.getTags())
                .add("note", person.getNote())
                .add("noteDate", person.getNoteDate())
                .toString();
    }

    /**
     * Formats the {@code applicant} for display to the user.
     */
    public static String format(Applicant applicant) {
        final StringBuilder builder = new StringBuilder();
        builder.append(applicant.getName())
            .append("; Phone: ")
            .append(applicant.getPhone())
            .append("; Email: ")
            .append(applicant.getEmail())
            .append("; Address: ")
            .append(applicant.getAddress())
            .append("; Role: ")
            .append(applicant.getRole())
            .append("; Stage: ")
            .append(applicant.getStage())
            .append("; Tags: ");
        applicant.getTags().forEach(builder::append);
        return builder.toString();
    }

}
