package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Bolt;
import seedu.address.model.student.Email;
import seedu.address.model.student.Major;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Star;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_STAR = "Star is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_BOLT = "Bolt is not a non-zero unsigned integer.";


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
     * Parses a {@code String major} into an {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return new Major(trimmedMajor);
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
     * Parses a {@code String star} into a {@code Star}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Star} is invalid.
     */
    public static Star parseStar(String star) throws ParseException {
        requireNonNull(star);
        String trimmedStar = star.trim(); // trim the string to only get the number
        if (!StringUtil.isUnsignedInteger(trimmedStar)) {
            throw new ParseException(MESSAGE_INVALID_STAR);
        } // checks if Star is in fact an Integer
        Integer starCount = Integer.parseInt(trimmedStar);
        if (!Star.isValidStar(starCount)) { // check whether star is a valid Integer >= 0
            throw new ParseException(Star.MESSAGE_CONSTRAINTS);
        }
        return new Star(starCount);
    }

    /**
     * Parses a {@code String bolt} into a {@code Bolt}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Bolt} is invalid.
     */
    public static Bolt parseBolt(String bolt) throws ParseException {
        requireNonNull(bolt);
        String trimmedBolt = bolt.trim(); // trim the string to only get the number
        if (!StringUtil.isUnsignedInteger(trimmedBolt)) {
            throw new ParseException(MESSAGE_INVALID_BOLT);
        } // checks if Bolt is in fact an Integer
        Integer boltCount = Integer.parseInt(trimmedBolt);
        if (!Bolt.isValidBolt(boltCount)) { // check whether bolt is a valid Integer >= 0
            throw new ParseException(Bolt.MESSAGE_CONSTRAINTS);
        }
        return new Bolt(boltCount);
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
