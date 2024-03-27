package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grade.Grade;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.timeslots.Timeslots;

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
     * Parses a {@code String timeslot} into a {@code Timeslot}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timeslot} is invalid.
     */
    public static Timeslots parseTimeslot(String timeslot) throws ParseException {
        requireNonNull(timeslot);
        String trimmedTimeslot = timeslot.trim();
        if (!Timeslots.isValidTimeslot(trimmedTimeslot)) {
            throw new ParseException(Timeslots.MESSAGE_CONSTRAINTS);
        }
        return new Timeslots(trimmedTimeslot);
    }

    /**
     * Parses {@code Collection<String> timeslots} into a {@code Set<Timeslot>}.
     */
    public static Set<Timeslots> parseTimeslots(Collection<String> timeslots) throws ParseException {
        requireNonNull(timeslots);
        final Set<Timeslots> timeslotsSet = new HashSet<>();
        for (String timeslot : timeslots) {
            timeslotsSet.add(parseTimeslot(timeslot));
        }
        return timeslotsSet;
    }

    /**
     * Parses a {@code String grade} into a {@code grade}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code grade} is invalid.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim();
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Timeslots.MESSAGE_CONSTRAINTS);
        }
        return new Grade(trimmedGrade);
    }

    /**
     * Parses {@code Collection<String> grades} into a {@code Set<Grade>}.
     */
    public static Set<Grade> parseGrades(Collection<String> grades) throws ParseException {
        requireNonNull(grades);
        final Set<Grade> gradeSet = new HashSet<>();
        for (String grade : grades) {
            gradeSet.add(parseGrade(grade));
        }
        return gradeSet;
    }
}
