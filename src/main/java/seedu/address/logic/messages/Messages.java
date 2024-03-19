package seedu.address.logic.messages;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_NAME = "The name provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_EDIT_PERSON = "The name provided is invalid. \n "
            + " Make sure that you are attempting to edit OTHERS.";
    public static final String MESSAGE_INVALID_EDIT_STAFF = "The name provided is invalid. \n "
            + "Make sure that you are attempting to edit STAFF.";
    public static final String MESSAGE_INVALID_EDIT_MAINTAINER = "The name provided is invalid. \n "
            + " Make sure that you are attempting to edit MAINTAINER.";
    public static final String MESSAGE_INVALID_EDIT_SUPPLIER = "The name provided is invalid. \n "
            + " Make sure that you are attempting to edit SUPPLIER.";

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
     * Formats the {@code staff} for display to the user.
     */
    public static String format(Staff person) {
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
        builder.append("; Salary: ")
                .append(person.getSalary())
                .append("; Employment: ")
                .append(person.getEmployment());
        return builder.toString();
    }

    /**
     * Formats the {@code maintenance} for display to the user.
     */
    public static String format(Maintainer person) {
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
        builder.append("; Skill: ")
                .append(person.getSkill())
                .append("; Commission: ")
                .append(person.getCommission());
        return builder.toString();
    }

    /**
     * Formats the {@code supplier} for display to the user.
     */
    public static String format(Supplier person) {
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
        builder.append("; Product: ")
                .append(person.getProduct())
                .append("; Price: ")
                .append(person.getPrice());
        return builder.toString();
    }
}
