package seedu.realodex.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.commons.util.StringUtil;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.remark.Remark;
import seedu.realodex.model.tag.Tag;


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
        return new Name(capitalizeWords(trimmedName));
    }

    /**
     * Parses a {@code String name} into a {@code Name} and returns it along with any parsing exception message.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name The name string to parse.
     * @return A ParserUtilResult containing the parsed Name or an exception message.
     */
    public static ParserUtilResult<Name> parseNameReturnStored(String name) {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            return new ParserUtilResult<>(Name.MESSAGE_CONSTRAINTS, new Name());
        }
        return new ParserUtilResult<>("", new Name(capitalizeWords(trimmedName)));
    }

    /**
     * Capitalizes the first letter of each word in the given sentence.
     * All other parts of the same word will be lower-cased.
     *
     * @param sentence the input sentence
     * @return the sentence with the first letter of each word capitalized
     */
    public static String capitalizeWords(String sentence) {
        requireNonNull(sentence);
        if (sentence.isEmpty()) {
            return sentence;
        }
        // Split the sentence into words
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            // Capitalize the first letter and append the rest of the word in lowercase
            sb.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return sb.toString().trim();
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
     * Parses a {@code String phone} into a {@code Phone} and returns it along with any parsing exception message.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param phone The phone string to parse.
     * @return A ParserUtilResult containing the parsed Phone or an exception message.
     */
    public static ParserUtilResult<Phone> parsePhoneReturnStored(String phone) {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            return new ParserUtilResult<>(Phone.MESSAGE_CONSTRAINTS, new Phone());
        }
        return new ParserUtilResult<>("", new Phone(trimmedPhone));
    }

    /**
     * Parses a {@code String income} into a {@code Income}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code income} is invalid.
     */
    public static Income parseIncome(String income) throws ParseException {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!Income.isValidIncome(trimmedIncome)) {
            throw new ParseException(Income.MESSAGE_CONSTRAINTS);
        }
        return new Income(trimmedIncome);
    }

    /**
     * Parses a {@code String income} into a {@code Income} and returns it along with any parsing exception message.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param income The income string to parse.
     * @return A ParserUtilResult containing the parsed Income or an exception message.
     */
    public static ParserUtilResult<Income> parseIncomeReturnStored(String income) {
        requireNonNull(income);
        String trimmedIncome = income.trim();
        if (!Income.isValidIncome(trimmedIncome)) {
            return new ParserUtilResult<>(Income.MESSAGE_CONSTRAINTS, new Income());
        }
        return new ParserUtilResult<>("", new Income(trimmedIncome));
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address The address string to parse.
     * @return A ParserUtilResult containing the parsed Address or an exception message.
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static ParserUtilResult<Address> parseAddressReturnStored(String address) {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            return new ParserUtilResult<>(Address.MESSAGE_CONSTRAINTS, new Address());
        }
        return new ParserUtilResult<>("", new Address(trimmedAddress));
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
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email The email string to parse.
     * @return A ParserUtilResult containing the parsed Email or an exception message.
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static ParserUtilResult<Email> parseEmailReturnStored(String email) {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            return new ParserUtilResult<>(Email.MESSAGE_CONSTRAINTS, new Email());
        }
        return new ParserUtilResult<>("", new Email(trimmedEmail));
    }

    /**
     * Parses a {@code String family} into a {@code Family}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code family} is invalid.
     */
    public static Family parseFamily(String family) throws ParseException {
        requireNonNull(family);
        String trimmedFamily = family.trim();
        if (!Family.isValidFamily(trimmedFamily)) {
            throw new ParseException(Family.MESSAGE_CONSTRAINTS);
        }
        assert Integer.parseInt(trimmedFamily) >= 1;
        return new Family(trimmedFamily);
    }

    /**
     * Parses a {@code String family} into a {@code Family}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param family The family string to parse.
     * @return A ParserUtilResult containing the parsed Family or an exception message.
     * @throws ParseException if the given {@code family} is invalid.
     */
    public static ParserUtilResult<Family> parseFamilyReturnStored(String family) {
        requireNonNull(family);
        String trimmedFamily = family.trim();
        if (!Family.isValidFamily(trimmedFamily)) {
            return new ParserUtilResult<>(Family.MESSAGE_CONSTRAINTS, new Family());
        }
        assert Integer.parseInt(trimmedFamily) >= 1;
        return new ParserUtilResult<>("", new Family(trimmedFamily));
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
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Remark parseRemark(String remark) {
        String trimmedRemark = remark.trim();
        return new Remark(trimmedRemark);
    }
}
