package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.course.Course;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.weeknumber.WeekNumber;

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
     * Parses a {@code String nusNet} into a {@code NusNet}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nusNet} is invalid.
     */
    public static NusNet parseNusNet(String nusNet) throws ParseException {
        requireNonNull(nusNet);
        String trimmedNusNet = nusNet.trim();
        if (!NusNet.isValidNusNet(trimmedNusNet)) {
            throw new ParseException(NusNet.MESSAGE_CONSTRAINTS);
        }
        return new NusNet(trimmedNusNet);
    }

    /**
     * Parses a {@code String code} into a {@code Course}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static Course parseCourse(String code) throws ParseException {
        requireNonNull(code);
        String trimmedCode = code.trim().toUpperCase();
        if (!Tag.isValidTagName(trimmedCode)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(trimmedCode);
    }

    /**
     * Parses a {@code String weekNumber} into a {@code WeekNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weekNumber} is invalid.
     */
    public static WeekNumber parseWeekNumber(String weekNumber) throws ParseException {
        requireNonNull(weekNumber);
        String trimmedWeekNumber = weekNumber.trim();
        // Assuming WeekNumber class has a static method isValidWeekNumber to validate the week number
        if (!WeekNumber.isValidWeekNumber(trimmedWeekNumber)) {
            throw new ParseException(WeekNumber.MESSAGE_CONSTRAINTS);
        }
        return new WeekNumber(trimmedWeekNumber);
    }

    /**
     * Parses {@code Collection<String> attendance} into a {@code Set<WeekNumber>}.
     *
     * @throws ParseException if any of the given {@code attendance} strings are invalid.
     */
    public static Set<WeekNumber> parseAttendance(Collection<String> attendance) throws ParseException {
        requireNonNull(attendance);
        final Set<WeekNumber> attendanceSet = new HashSet<>();
        for (String weekNumberStr : attendance) {
            attendanceSet.add(parseWeekNumber(weekNumberStr));
        }
        return attendanceSet;
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
}
