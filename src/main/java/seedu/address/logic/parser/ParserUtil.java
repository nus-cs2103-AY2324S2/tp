package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jdk.jshell.spi.ExecutionControl;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses the given National Registration Identification Card (NRIC) string into a {@code NRIC} object.
     * <p>
     * The input string is required to be non-null, and the parsing is performed after trimming any
     * leading or trailing spaces. If the trimmed NRIC string does not meet the validity constraints specified
     * in the {@code NRIC} class (as determined by {@link Nric#isValidNric(String)}), a {@code ParseException}
     * is thrown with the corresponding error message.
     * </p>
     * <p>
     * Example of a valid NRIC string: "S1234567A".
     * </p>
     *
     * @param nric The National Registration Identification Card (NRIC) string to be parsed.
     * @return A {@code NRIC} object representing the parsed NRIC.
     * @throws ParseException If the input string is null, empty, or does not meet the validity constraints.
     * @see Nric#isValidNric(String)
     * @see Nric
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
     * Parses the given date of birth (DoB) string into a {@code DoB} object.
     * <p>
     * The input string is required to be non-null, and the parsing is performed after trimming any leading
     * or trailing spaces. If the trimmed DoB string does not meet the validity constraints specified in the
     * {@code DoB} class (as determined by {@link DoB#isValidDoB(String)}), a {@code ParseException} is thrown
     * with the corresponding error message.
     * </p>
     * <p>
     * Example of a valid DoB string: "3 January 2000".
     * </p>
     *
     * @param dob The date of birth string to be parsed.
     * @return A {@code DoB} object representing the parsed date of birth.
     * @throws ParseException If the input string is null, empty, or does not meet the validity constraints.
     * @see DoB#isValidDoB(String)
     * @see DoB
     */
    public static DoB parseDoB(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmedDoB = dob.trim();
        if (!DoB.isValidDoB(trimmedDoB)) {
            throw new ParseException(DoB.MESSAGE_CONSTRAINTS);
        }
        return new DoB(trimmedDoB);
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
        return new Address(trimmedAddress);
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
        return new Tag(trimmedTag);
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

    public static AppointmentDate parseAppointmentDate(String apptDate) throws ParseException {
        requireNonNull(apptDate);
        String trimmedDate = apptDate.trim();
        if (!AppointmentDate.isValidDate(trimmedDate)) {
            throw new ParseException(AppointmentDate.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentDate(trimmedDate);
    }
}
