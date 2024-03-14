package educonnect.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import educonnect.commons.core.index.Index;
import educonnect.commons.util.StringUtil;
import educonnect.logic.parser.exceptions.ParseException;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.tag.Tag;

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
        String capitalName = createCapitalName(trimmedName);
        return new Name(capitalName);
    }

    /**
     * Capitalises the names separated by a space
     * Removes unecessary spaces in between names
     */
    public static String createCapitalName(String trimmedName) {
        String[] words = trimmedName.split("[\\s-]+");
        StringBuilder namesUnparsed = new StringBuilder(trimmedName);
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            int lengthOfWord = word.length();
            int indexOfWord = namesUnparsed.indexOf(word);

            //Appends the character behind the individual word
            if (indexOfWord != 0) {
                result.append(namesUnparsed.charAt(indexOfWord - 1));
            }

            result.append(Character.toUpperCase(word.charAt(0)));

            if (word.length() > 1) {
                result.append(word.substring(1));
            }
            namesUnparsed.delete(0, indexOfWord + lengthOfWord);
        }
        String capitalised = result.toString();
        return capitalised;
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        String capitalised = createCapitalStudentId(trimmedId);
        return new StudentId(capitalised);
    }

    /**
     * Capitalises the 'A' at the start of the Student Id
     * @param trimmedId
     * @return Capitalised String of Student ID
     */
    public static String createCapitalStudentId(String trimmedId) {
        StringBuilder result = new StringBuilder();
        result.append(Character.toUpperCase(trimmedId.charAt(0)));
        result.append(trimmedId.substring(1));
        String capitalised = result.toString();
        return capitalised;
    }

    /**
     * Parses a {@code telegramHandle} into a {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegramHandle} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String telegramHandle) throws ParseException {
        requireNonNull(telegramHandle);
        String trimmedHandle = telegramHandle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedHandle);
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
}
