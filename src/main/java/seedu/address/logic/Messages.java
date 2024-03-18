package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.startup.Startup;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX = "The startup index provided is invalid";
    public static final String MESSAGE_STARTUPS_LISTED_OVERVIEW = "%1$d startups listed!";
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
     * Formats the {@code startup} for display to the user.
     */
    public static String format(Startup startup) {
        final StringBuilder builder = new StringBuilder();
        builder.append(startup.getName())
                .append("; Industry: ")
                .append(startup.getIndustry())
                .append("; Funding Stage: ")
                .append(startup.getFundingStage())
                .append("; Phone: ")
                .append(startup.getPhone())
                .append("; Email: ")
                .append(startup.getEmail())
                .append("; Address: ")
                .append(startup.getAddress())
                .append("; Note: ")
                .append(startup.getNote())
                .append("; Tags: ");
        startup.getTags().forEach(builder::append);
        return builder.toString();
    }

}
