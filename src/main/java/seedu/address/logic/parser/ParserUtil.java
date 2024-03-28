package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.DrugAllergy;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.illness.Illness;
import seedu.address.model.person.note.Description;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE_TIME = "Date or time format is invalid.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses the input string containing one or more whitespace-separated indices into {@code Index} object array.
     * Leading and trailing whitespaces in the input string and around individual indices will be trimmed.
     *
     * @param indicesString the string containing one or more one-based indices separated by whitespace.
     * @return an array of {@code Index} objects corresponding to the input indices.
     * @throws ParseException if any of the specified indices are invalid (not non-zero unsigned integers).
     */
    public static Index[] parseIndices(String indicesString) throws ParseException {
        String[] parts = indicesString.trim().split("\\s+");
        Index[] indices = new Index[parts.length];

        for (int i = 0; i < parts.length; i++) {
            indices[i] = parseIndex(parts[i]);
        }
        return indices;
    }


    /**
     * Parses a {@code String Nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gender} is invalid.
     */
    public static Gender parseGender(String gender) throws ParseException {
        String trimmedGender;
        if (gender == null) {
            trimmedGender = "Prefer not to say";
        } else {
            trimmedGender = gender.trim();
        }

        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String birthDate} into a {@code BirthDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthDate} is invalid.
     */
    public static BirthDate parseBirthDate(String birthDate) throws ParseException {
        requireNonNull(birthDate);
        String trimmedBirthDate = birthDate.trim();
        if (!BirthDate.isValidBirthDate(trimmedBirthDate)) {
            throw new ParseException(BirthDate.MESSAGE_CONSTRAINTS);
        }

        return new BirthDate(trimmedBirthDate);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }


    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String illness} into a {@code Illness}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code illness} is invalid.
     */
    public static Illness parseIllness(String illness) throws ParseException {
        requireNonNull(illness);
        String trimmedIllness = illness.trim();
        if (!Illness.isValidIllnessName(trimmedIllness)) {
            throw new ParseException(Illness.MESSAGE_CONSTRAINTS);
        }
        return new Illness(trimmedIllness);
    }

    /**
     * Parses {@code Collection<String> illnesses} into a {@code Set<Illness>}.
     */
    public static Set<Illness> parseIllnesses(Collection<String> illnesses) throws ParseException {
        requireNonNull(illnesses);
        final Set<Illness> illnessSet = new HashSet<>();
        for (String illnessName : illnesses) {
            illnessSet.add(parseIllness(illnessName));
        }
        return illnessSet;
    }

    /**
     * Parses a {@code String drugAllergy} into a {@code Drug Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static DrugAllergy parseDrugAllergy(String drugAllergy) throws ParseException {
        String trimmedDrugAllergy;
        if (drugAllergy == null) {
            trimmedDrugAllergy = "No allergy";
        } else {
            trimmedDrugAllergy = drugAllergy.trim();
        }

        if (!DrugAllergy.isValidDrugAllergy(trimmedDrugAllergy)) {
            throw new ParseException(DrugAllergy.MESSAGE_CONSTRAINTS);
        }

        return new DrugAllergy(trimmedDrugAllergy);
    }

    /**
     * Parses {@code String date} and {@code String time} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} or {@code time} is invalid.
     */
    public static LocalDateTime parseLocalDateTime(String date, String time) throws ParseException {
        requireAllNonNull(date, time);

        String trimmedDate = date.trim();
        String trimmedTime = time.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            return LocalDateTime.parse(trimmedDate + " " + trimmedTime, formatter);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Parses {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseLocalDate(String date) throws ParseException {
        requireAllNonNull(date);

        String trimmedDate = date.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            return LocalDate.parse(trimmedDate, formatter);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Parses {@code String time} into a {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseLocalTime(String time) throws ParseException {
        requireAllNonNull(time);

        String trimmedTime = time.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

        try {
            return LocalTime.parse(trimmedTime, formatter);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();

        if (!Description.isValid(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }

        return new Description(trimmedDescription);
    }
}
