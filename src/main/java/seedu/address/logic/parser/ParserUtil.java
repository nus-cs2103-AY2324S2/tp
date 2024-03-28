package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Info;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
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
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
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
     * Reads the input for interview-time tag
     * @param dateTime given
     * @return trimmed output to be stored
     * @throws ParseException if invalid format
     */
    public static InterviewTime parseInterviewTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!InterviewTime.isValidInterviewTime(trimmedDateTime)) {
            throw new ParseException(InterviewTime.MESSAGE_CONSTRAINTS);
        }
        return new InterviewTime(trimmedDateTime);
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
     * Parses a {@code String salary} into an {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        if (!Salary.isValidSalary(trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(trimmedSalary);
    }

    /**
     * Parses a {@code String info} into an {@code Info}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Info parseInfo(String info) {
        String trimmedInfo = info.trim();
        return new Info(trimmedInfo);
    }

    /**
     * Parses a string representing a programming language into a {@code ProgrammingLanguage} object.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param programmingLanguageString A string representing a programming language.
     * @return A {@code ProgrammingLanguage} object parsed from the input string.
     * @throws ParseException if the given {@code programmingLanguageString} is invalid.
     */
    public static ProgrammingLanguage parseProgrammingLanguage(String programmingLanguageString) throws ParseException {
        requireNonNull(programmingLanguageString);
        String trimmedProgrammingLanguage = programmingLanguageString.trim();
        if (!ProgrammingLanguage.isValidLanguageName(trimmedProgrammingLanguage)) {
            throw new ParseException(ProgrammingLanguage.MESSAGE_CONSTRAINTS);
        }
        return new ProgrammingLanguage(trimmedProgrammingLanguage);
    }

    /**
     * Parses collection of strings representing programming languages into set of {@code ProgrammingLanguage} objects.
     *
     * @param programmingLanguages A collection of strings representing programming languages.
     * @return A set of {@code ProgrammingLanguage} objects parsed from the input collection.
     * @throws ParseException if any of the programming language strings are invalid.
     */
    public static Set<ProgrammingLanguage> parseProgrammingLanguages(Collection<String> programmingLanguages)
            throws ParseException {
        requireNonNull(programmingLanguages);
        final Set<ProgrammingLanguage> programmingLanguageSet = new HashSet<>();
        for (String programmingLanguage : programmingLanguages) {
            programmingLanguageSet.add(parseProgrammingLanguage(programmingLanguage));
        }
        return programmingLanguageSet;
    }

    /**
     * Parses a string representing a priority into an integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param priority A string representing a priority.
     * @return An integer parsed from the input string.
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static int parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        return Integer.parseInt(trimmedPriority);
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    private static boolean isValidPriority(String test) {
        String validRegex = "^[0-4]$";
        return test.matches(validRegex);
    }

}
