package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;

/**
 * Container for user visible messages.
 */
public class InternshipMessages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX = "The internship index provided is invalid";
    public static final String MESSAGE_INTERNSHIPS_LISTED_OVERVIEW = "%1$d internships listed!";
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
    public static String format(Internship internship) {
        final StringBuilder builder = new StringBuilder();
        builder.append(internship.getCompanyName())
                .append("; Company Name: ")
                .append(internship.getLocation())
                .append("; Location: ")
                .append(internship.getDescription())
                .append("; Description: ")
                .append(internship.getRole())
                .append("; Role: ")
                .append(internship.getContactName())
                .append("; Contact Name: ")
                .append(internship.getContactEmail())
                .append("; Contact Email: ")
                .append(internship.getContactNumber())
                .append("; Contact Number: ")
                .append(internship.getApplicationStatus())
                .append("; Application Status: ");
        return builder.toString();
    }

}
