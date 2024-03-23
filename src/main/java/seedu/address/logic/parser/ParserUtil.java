package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.AttendanceStatus;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Attendance;

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
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
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
     * Parses a {@code String course code} into an {@code Classes}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code courseCode} is invalid.
     */
    public static Classes parseClass(String courseCode) throws ParseException {
        requireNonNull(courseCode);
        String trimmedCourseCode = courseCode.trim();
        if (!CourseCode.isValidClass(trimmedCourseCode)) {
            throw new ParseException(CourseCode.MESSAGE_CONSTRAINTS);
        }
        return new Classes(new CourseCode(trimmedCourseCode), new AddressBook());
    }

    /**
     * Parses a {@code String date} into an {@code AttendanceDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Attendance parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Attendance.isValidDate(trimmedDate)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(new AttendanceStatus(trimmedDate, "1"));
    }

    /**
     * Parses a {@code String date} into a {@code Attendance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code attendance} is invalid.
     */
    public static Attendance parseAttendances(String date) throws ParseException {
        requireNonNull(date);
        String trimmedTag = date.trim();
        if (!Attendance.isValidDate(trimmedTag)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(new AttendanceStatus(trimmedTag, "1"));
    }

    /**
     * Parses {@code Collection<String> attendances} into a {@code Set<Attendance>}.
     */
    public static Set<Attendance> parseAttendances(Collection<String> attendances) throws ParseException {
        requireNonNull(attendances);
        final Set<Attendance> attendanceSet = new HashSet<>();
        for (String attendanceName : attendances) {
            attendanceSet.add(parseAttendances(attendanceName));
        }
        return attendanceSet;
    }

    /**
     * Parses {@code String date} and {@code String status} into a {@code AttendanceStatus}.
     */
    public static AttendanceStatus parsesAttendanceStatus(String date, String status) throws ParseException {
        requireNonNull(date, status);
        String trimmedDate = date.trim();
        String trimmedStatus = status.trim();
        if (!Attendance.isValidDate(trimmedDate) || !Attendance.isValidStatus(trimmedStatus)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new AttendanceStatus(date, status);
    }
}
