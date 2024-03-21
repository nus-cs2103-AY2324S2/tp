package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Client;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_ID = "There is no person with id %1$d";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_CLIENT_PROPERTY = "Client cannot have department, job title, skills, "
            + "and terms of service fields";
    public static final String MESSAGE_INVALID_EMPLOYEE_PROPERTY = "Employee cannot have preferences, products, "
            + "and terms of service fields";
    public static final String MESSAGE_INVALID_SUPPLIER_PROPERTY = "Supplier cannot have department, job title, "
            + "and skills fields";

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
                .append("; Id: ")
                .append(person.getId())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        builder.append("; Remark: ").append(person.getRemark());
        if (person instanceof Client) {
            builder.append("; Products: ").append(((Client) person).getProducts())
                    .append("; Preferences: ").append(((Client) person).getPreferences());
        } else if (person instanceof Employee) {
            builder.append("; Department: ").append(((Employee) person).getDepartment())
                    .append("; Job Title: ").append(((Employee) person).getJobTitle())
                    .append("; Skills: ").append(((Employee) person).getSkills());
        } else if (person instanceof Supplier) {
            builder.append("; Products: ").append(((Supplier) person).getProducts())
                    .append("; Terms of Service: ").append(((Supplier) person).getTermsOfService());
        }
        return builder.toString();
    }

}
