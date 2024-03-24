package seedu.address.logic;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Allergies;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Country;
import seedu.address.model.person.DateOfAdmission;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Symptom;

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
        Optional<Email> email = Optional.ofNullable(person.getEmail());
        Optional<Country> country = Optional.ofNullable(person.getCountry());
        Optional<Allergies> allergies = Optional.ofNullable(person.getAllergies());
        Optional<BloodType> bloodType = Optional.ofNullable(person.getBloodType());
        Optional<Condition> condition = Optional.ofNullable(person.getCondition());
        Optional<DateOfAdmission> dateOfAdmission = Optional.ofNullable(person.getDateOfAdmission());
        Optional<Diagnosis> diagnosis = Optional.ofNullable(person.getDiagnosis());
        Optional<Symptom> symptom = Optional.ofNullable(person.getSymptom());
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

}
