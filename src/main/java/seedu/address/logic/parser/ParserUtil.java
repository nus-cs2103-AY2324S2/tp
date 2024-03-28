package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.TAG_INVALID_INDEX;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.YearJoined;
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
     * Parses {@code id} into an {@code id} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified id is invalid (not 6-digit number).
     */
    public static Id parseId(String id) throws ParseException {
        String trimmedIndex = id.trim();
        if (!StringUtil.isSixDigitNumber(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new Id(Integer.parseInt(trimmedIndex));
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
        String trimmedAddress = address.replace(":tag", "").trim();
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

    /**
     * Returns a pair of valid index and new tag name.
     * @param tag Specific tag name to be updated.
     * @return A pair of valid index and tag name.
     * @throws ParseException Alerts users for invalid input.
     */
    public static Pair<Integer, String> parseUpdatedTags(Optional<String> tag) throws ParseException {
        requireNonNull(tag);
        String[] tagInfo = tag.get().split("\\s+", 2);
        if (Integer.valueOf(tagInfo[0]) < -1) {
            throw new ParseException(TAG_INVALID_INDEX);
        }
        if (Integer.valueOf(tagInfo[0]) == -1) {
            return new Pair<>(Integer.valueOf(tagInfo[0]), null);
        }
        if (tagInfo.length <= 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        if (!Tag.isValidTagName(tagInfo[1])) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        } else {
            return new Pair<>(Integer.valueOf(tagInfo[0]), tagInfo[1]);
        }
    }

    /**
     * Parses a {@code String yearJoined} into a {@code YearJoined}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code yearJoined} is invalid.
     */
    public static YearJoined parseYearJoined(String yearJoined) throws ParseException {
        requireNonNull(yearJoined);
        String trimmedYearJoined = yearJoined.trim();
        if (!YearJoined.isValidYearJoined(trimmedYearJoined)) {
            throw new ParseException(YearJoined.MESSAGE_CONSTRAINTS);
        }
        return new YearJoined(trimmedYearJoined);
    }
}
