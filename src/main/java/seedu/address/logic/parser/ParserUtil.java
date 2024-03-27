package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Week;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

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
    public static Optional<Phone> parsePhone(String phone) throws ParseException {
        if (phone.isEmpty()) {
            return Optional.of(Phone.EMPTY);
        }
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Phone(trimmedPhone));
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
     * Parses a {@code String classGroup} into an {@code ClassGroup}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code classGroup} is invalid.
     */
    public static ClassGroup parseClassGroup(String classGroup) throws ParseException {
        requireNonNull(classGroup);
        String trimmedClassGroup = classGroup.trim();
        if (!ClassGroup.isValidClassGroup(trimmedClassGroup)) {
            throw new ParseException(ClassGroup.MESSAGE_CONSTRAINTS);
        }
        return new ClassGroup(trimmedClassGroup);
    }

    /**
     * Parses a {@code String telegram} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Optional<Telegram> parseTelegram(String telegram) throws ParseException {
        if (telegram.isEmpty()) {
            return Optional.of(Telegram.EMPTY);
        }
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Telegram(trimmedTelegram));
    }

    /**
     * Parses a {@code String github} into an {@code Github}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code github} is invalid.
     */
    public static Optional<Github> parseGithub(String github) throws ParseException {
        if (github.isEmpty()) {
            return Optional.of(Github.EMPTY);
        }
        String trimmedGithub = github.trim();
        if (!Github.isValidGithub(trimmedGithub)) {
            throw new ParseException(ClassGroup.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Github(trimmedGithub));
    }

    /**
     * Parses a {@code String note} into an {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(note);
    }

    /**
     * Parses a {@code String week} into an {@code Week}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code week} is invalid.
     */
    public static Week parseWeek(String week) throws ParseException {
        requireNonNull(week);
        String trimmedWeek = week.trim();
        Index weekIndex = parseIndex(trimmedWeek);
        if (!Week.isValidWeek(weekIndex)) {
            throw new ParseException(Week.MESSAGE_CONSTRAINTS);
        }
        return new Week(weekIndex);
    }

    /**
     * Parses a {@code String indices} into a {@code List<Index>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code indices} is invalid.
     */
    public static List<Index> parseIndices(String indices) throws ParseException {
        requireNonNull(indices);
        String[] trimmedIndices = Arrays.stream(indices.trim().split(","))
                .map(String::trim)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        List<Index> indexList = new ArrayList<>();
        for (String index : trimmedIndices) {
            indexList.add(parseIndex(index));
        }
        return indexList;
    }
}
