package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Policy;
import seedu.address.model.person.Relationship;
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
     * Parses a {@code String relationship} into a {@code Relationship}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code relationship} is invalid.
     */
    public static Relationship parseRelationship(String relationship) throws ParseException {
        requireNonNull(relationship);
        String trimmedRelationship = relationship.trim();
        if (!Relationship.isValidRelationship(trimmedRelationship)) {
            throw new ParseException(Relationship.MESSAGE_CONSTRAINTS);
        }
        return new Relationship(trimmedRelationship);
    }

    /**
     * Parses a {@code String expiryDate} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code expiryDate} is invalid.
     */
    public static LocalDate parseExpiryDate(String expiryDate) throws ParseException {
        requireNonNull(expiryDate);
        String trimmedExpiryDate = expiryDate.trim();
        if (!Policy.isValidExpiryDate(trimmedExpiryDate)) {
            throw new ParseException(Policy.EXPIRY_DATE_MESSAGE_CONSTRAINTS);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(trimmedExpiryDate, formatter);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static double parsePremium(String premium) throws ParseException {
        requireNonNull(premium);
        String trimmedPremium = premium.trim();
        if (!Policy.isValidPremium(trimmedPremium)) {
            throw new ParseException(Policy.PREMIUM_MESSAGE_CONSTRAINTS);
        }
        return Double.parseDouble(trimmedPremium);
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


    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date format. Use YYYY-MM-DD.");
        }
    }


    /**
     * Parses a {@code String time} into a {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        try {
            return LocalTime.parse(trimmedTime, DateTimeFormatter.ISO_LOCAL_TIME);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid time format. Use HH:MM.");
        }
    }

    /**
     * Parses a {@code String durationStr} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code durationStr} is invalid.
     */
    public static Duration parseDuration(String durationStr) throws ParseException {
        requireNonNull(durationStr);
        try {
            long minutes = Long.parseLong(durationStr);
            return Duration.ofMinutes(minutes);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid duration format. Use minutes as an integer.");
        }
    }

}
