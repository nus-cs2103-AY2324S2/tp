package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Interest;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser
 * classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero
     *                        unsigned integer).
     */
    public static ArrayList<Index> parseIndexArrayList(String oneBasedIndex) throws ParseException {
        String trimmedIndexes = oneBasedIndex.trim();
        String[] indexArray = trimmedIndexes.split(", ");
        ArrayList<Index> indexArrayList = new ArrayList<>();

        for (String index : indexArray) {
            System.out.println(index);

            if (!StringUtil.isNonZeroUnsignedInteger(index)) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indexArrayList.add(Index.fromOneBased(Integer.parseInt(index)));
        }
        return indexArrayList;
    }

    /**
     * Parses {@code personNames} into an {@code personNamePredicateArrayList} and returns it. Leading
     * and trailing whitespaces will be
     * trimmed.
     */
    public static ArrayList<NameContainsKeywordsPredicate> parseNamePredicateArrayList(String personNames)
            throws ParseException {
        String trimmedPersonNames = personNames.trim();
        String[] personNameArray = trimmedPersonNames.split(", ");
        ArrayList<NameContainsKeywordsPredicate> personNamePredicateArrayList = new ArrayList<>();

        for (String personName : personNameArray) {
            System.out.println(personName);
            String[] nameString = {personName};

            personNamePredicateArrayList.add(
                    new NameContainsKeywordsPredicate(Arrays.asList(nameString)));
        }
        return personNamePredicateArrayList;
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
        // requireNonNull(address);
        if (Objects.equals(address, "")) {
            return new Address("");
        } else {
            String trimmedAddress = address.trim();
            if (!Address.isValidAddress(trimmedAddress)) {
                throw new ParseException(Address.MESSAGE_CONSTRAINTS);
            }
            return new Address(trimmedAddress);
        }
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
     * Parses a {@code String interest} into a {@code Interest}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interest} is invalid.
     */
    public static Tag parseInterest(String interest) throws ParseException {
        requireNonNull(interest);
        String trimmedTag = interest.trim();
        if (!Interest.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Interest(trimmedTag);
    }

    /**
     * Parses the given collections of strings into a set of tags.
     * Both the {@code tags} and {@code interests} collections are processed.
     * @param tags      A collection of strings representing tags.
     * @param interests A collection of strings representing interests.
     * @return A set of {@code Tag} objects representing the parsed tags and
     *         interests.
     * @throws ParseException If there is any error parsing the tags or interests.
     */
    public static Set<Tag> parseTags(Collection<String> tags, Collection<String> interests) throws ParseException {
        requireNonNull(tags);
        requireNonNull(interests);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        for (String interestName : interests) {
            tagSet.add(parseInterest(interestName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String name, startTime, endTime} into an {@code Schedule}.
     * @param schedule
     * @return Schedule
     * @throws ParseException
     */
    public static Schedule parseSchedule(String schedule) throws ParseException {
        requireNonNull(schedule);
        String[] formatinputs = schedule.split(" ");
        if (formatinputs.length != 3) {
            throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
        }
        String name = formatinputs[0];
        LocalDateTime startTime = LocalDateTime.parse(formatinputs[1]);
        LocalDateTime endTime = LocalDateTime.parse(formatinputs[2]);
        if (!Schedule.isValidSchedName(formatinputs[0])) {
            throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
        }
        if (!Schedule.isValidTiming(startTime, endTime)) {
            throw new ParseException(Schedule.MESSAGE_CONSTRAINTS);
        }
        return new Schedule(name, startTime, endTime);
    }

    /**
     * Parses a List of Schedules Strings and convert them into Schedule objects
     * @param schedules
     * @return ArrayList of Schedules
     * @throws ParseException
     */
    public static ArrayList<Schedule> parseSchedules(Collection<String> schedules) throws ParseException {
        requireNonNull(schedules);
        final ArrayList<Schedule> scheduler = new ArrayList<>();
        for (String schName : schedules) {
            scheduler.add(parseSchedule(schName));
        }
        return scheduler;
    }
}
