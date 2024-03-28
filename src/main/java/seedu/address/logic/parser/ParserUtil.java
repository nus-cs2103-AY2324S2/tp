package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.StringFormatter;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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

        String capitalisedName = StringFormatter.capitalizeWords(name);
        return new Name(capitalisedName);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String phone} into an array of 2 {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid and if less than 2 phone numbers are provided.
     */
    public static Phone[] parsePhone(String phones) throws ParseException {
        requireNonNull(phones);
        String[] splitPhones = phones.split(",");

        Phone[] phoneArray = new Phone[2];
        if (splitPhones.length == 2) {
            for (int i = 0; i < splitPhones.length; i++) {
                String trimmedPhone = splitPhones[i].trim();
                if (!Phone.isValidPhone(trimmedPhone)) {
                    throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
                }
                phoneArray[i] = new Phone(trimmedPhone);
            }
            return phoneArray;
        } else if (splitPhones.length == 1) {
            String trimmedPhone = splitPhones[0].trim();
            if (!Phone.isValidPhone(trimmedPhone)) {
                throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
            }
            phoneArray[0] = new Phone(trimmedPhone);
            phoneArray[1] = new Phone(trimmedPhone);
            return phoneArray;
        } else {
            throw new ParseException(Phone.INVALID_NUMBER_OF_PHONES);
        }

    }

    /**
     * Parses a {@code String phone} into an array of the {@code Phone} to edit and a {@code number} to designate
     * which parent phone number to edit.
     *
     * @throws ParseException if the given {@code phone} is invalid and if the second input is invalid.
     */
    public static String[] parsePhoneForEdit(String phone) throws ParseException {
        requireNonNull(phone);
        String[] splitInput = phone.split(",");

        if (splitInput.length != 2) {
            throw new ParseException(Phone.INVALID_EDIT_INPUT);
        }

        String trimmedPhone = splitInput[0].trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }

        String number = splitInput[1].trim();
        //Checks if it is either "1" or "2"
        if (!number.equals("1") && !number.equals("2")) {
            throw new ParseException(Phone.INVALID_EDIT_INPUT);
        }

        return splitInput;
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }

        String capitalizedAddress = StringFormatter.capitalizeWords(trimmedAddress);
        return new Address(capitalizedAddress);
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }

        String capitalizedTag = StringFormatter.capitalizeWords(trimmedTag);
        return new Tag(capitalizedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses formClass name into a FormClass instance.
     * @param formClass name of class
     * @return Classroom instance
     * @throws ParseException if the formClass name is invalid.
     */
    public static FormClass parseClass(String formClass) throws ParseException {
        requireNonNull(formClass);
        String trimmedClass = formClass.trim();
        if (!FormClass.isValidClassName(trimmedClass)) {
            throw new ParseException(FormClass.MESSAGE_CONSTRAINTS);
        }

        String capitalizedClass = StringFormatter.capitalizeWords(trimmedClass);
        return new FormClass(capitalizedClass);
    }
}
