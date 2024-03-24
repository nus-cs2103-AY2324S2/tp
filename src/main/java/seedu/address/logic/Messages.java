package seedu.address.logic;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "The person provided was not found";
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
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; NRIC: ")
                .append(person.getNric())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; DOB: ")
                .append(person.getDateOfBirth())
                .append("; Sex: ")
                .append(person.getSex())
                .append("; Status: ")
                .append(person.getStatus())
                .append("; Email: ")
                .append(Optional.ofNullable(person.getEmail()).map(Object::toString).orElse("-"))
                .append("; Country: ")
                .append(Optional.ofNullable(person.getCountry()).map(Object::toString).orElse("-"))
                .append("; Allergies: ")
                .append(Optional.ofNullable(person.getAllergies()).map(Object::toString).orElse("-"))
                .append("; Blood Type: ")
                .append(Optional.ofNullable(person.getBloodType()).map(Object::toString).orElse("-"))
                .append("; Condition: ")
                .append(Optional.ofNullable(person.getCondition()).map(Object::toString).orElse("-"))
                .append("; DOA: ")
                .append(Optional.ofNullable(person.getDateOfAdmission()).map(Object::toString).orElse("-"))
                .append("; Diagnosis: ")
                .append(Optional.ofNullable(person.getDiagnosis()).map(Object::toString).orElse("-"))
                .append("; Symptom: ")
                .append(Optional.ofNullable(person.getSymptom()).map(Object::toString).orElse("-"))
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code person} for display to the user in organised manner.
     */
    public static String formatRead(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(person.getName()).append("\n")
                .append("NRIC: ").append(person.getNric()).append("\n")
                .append("Phone: ").append(person.getPhone()).append("\n")
                .append("Address: ").append(person.getAddress()).append("\n")
                .append("DOB: ").append(person.getDateOfBirth()).append("\n")
                .append("Sex: ").append(person.getSex()).append("\n")
                .append("Status: ").append(person.getStatus()).append("\n")
                .append("Email: ")
                .append(Optional.ofNullable(person.getEmail()).map(Object::toString).orElse("-")).append("\n")
                .append("Country: ")
                .append(Optional.ofNullable(person.getCountry()).map(Object::toString).orElse("-")).append("\n")
                .append("Allergies: ")
                .append(Optional.ofNullable(person.getAllergies()).map(Object::toString).orElse("-")).append("\n")
                .append("Blood Type: ")
                .append(Optional.ofNullable(person.getBloodType()).map(Object::toString).orElse("-")).append("\n")
                .append("Condition: ")
                .append(Optional.ofNullable(person.getCondition()).map(Object::toString).orElse("-")).append("\n")
                .append("DOA: ")
                .append(Optional.ofNullable(person.getDateOfAdmission()).map(Object::toString).orElse("-"))
                .append("\n")
                .append("Diagnosis: ")
                .append(Optional.ofNullable(person.getDiagnosis()).map(Object::toString).orElse("-")).append("\n")
                .append("Symptom: ")
                .append(Optional.ofNullable(person.getSymptom()).map(Object::toString).orElse("-")).append("\n")
                .append("Tags: ");
        person.getTags().forEach(tag -> builder.append(tag).append(", "));
        if (!person.getTags().isEmpty()) {
            builder.delete(builder.length() - 2, builder.length());
        }
        return builder.toString();
    }

}
