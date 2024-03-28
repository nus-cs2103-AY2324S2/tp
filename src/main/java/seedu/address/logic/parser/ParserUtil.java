package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
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
        String trimmedName = StringUtil.trimWhitespace(name);
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
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        // Store all emails in lowercase by default. Do not allow capitalisations
        trimmedEmail = trimmedEmail.toLowerCase();

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
     * Parses a {@code String dateTime} into an {@code LocalDateTime}.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();

        LocalDateTime localDateTime = DateUtil.parseDateTime(trimmedDateTime);
        if (localDateTime == null) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_TIME);
        }
        return localDateTime;
    }

    /**
     * Parses a {@code Collection<String> attend} into a {@code boolean}.
     * By default, false will be returned if {@code attend} is empty.
     *
     * @throws ParseException if the given {@code attend} is invalid.
     */
    public static boolean parseHasAttended(String attend) throws ParseException {
        requireNonNull(attend);
        if (attend.isEmpty()) {
            return false;
        }

        String trimmedAttend = attend.trim();
        if (!trimmedAttend.equalsIgnoreCase("true")
                && !trimmedAttend.equalsIgnoreCase("false")) {
            throw new ParseException(Messages.MESSAGE_INVALID_BOOLEAN_VALUE);
        }
        return Boolean.parseBoolean(trimmedAttend);
    }

    /**
     * Parses a {@code Collection<String> description} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     * By default, an empty string will be returned if {@code description} is empty.
     * TODO: remove after case log is implemented
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        if (description.isEmpty()) {
            return "";
        }
        return description.trim();
    }

    /**
     * Parses a {@code String feedbackScore} into an {@code Integer} and ensures that it is a valid number and is
     * between 1 and 5 (inclusive).
     *
     * @throws ParseException
     */
    public static Integer parseFeedbackScore(String feedbackScore) throws ParseException {
        if (feedbackScore.isEmpty()) {
            return null;
        }
        String trimmedFeedbackScore = feedbackScore.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedFeedbackScore)) {
            throw new ParseException(Messages.MESSAGE_INVALID_FEEDBACK_SCORE);
        }
        int feedbackScoreInt = Integer.parseInt(trimmedFeedbackScore);
        if (feedbackScoreInt < 1 || feedbackScoreInt > 5) {
            throw new ParseException(Messages.MESSAGE_INVALID_FEEDBACK_SCORE);
        }
        return Integer.parseInt(trimmedFeedbackScore);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean hasAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
