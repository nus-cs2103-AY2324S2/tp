package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson implements Comparable<Lesson> {
    public static final String MESSAGE_CONSTRAINTS =
            "Lessons should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[a-zA-Z][a-zA-Z ]*$";
    public static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String TIME_REGEX = "\\d{2}:\\d{2}";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public final String value;
    private final Subject subject;
    private final LocalDate date;
    private final LocalTime time;
    private LessonStatus status;

    /**
     * Constructs a Lesson with the specified subject, date, and time.
     *
     * @param subject The subject of the lesson.
     * @param date    The date of the lesson.
     * @param time    The time of the lesson.
     */
    public Lesson(String subject, String date, String time) {
        this.subject = new Subject(subject);
        this.date = LocalDate.parse(date, dateFormatter);
        this.time = LocalTime.parse(time, timeFormatter);
        this.status = LessonStatus.INCOMPLETE;
        this.value = subject;
    }

    /**
     * Returns true if a given string is a valid lesson.
     */
    public static boolean isValidLesson(String[] lessonDetails) {
        if (lessonDetails.length != 3) {
            return false;
        }
        try {
            LocalDate.parse(lessonDetails[1], dateFormatter);
            LocalTime.parse(lessonDetails[2], timeFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return lessonDetails[0].matches(VALIDATION_REGEX)
                && lessonDetails[1].matches(DATE_REGEX)
                && lessonDetails[2].matches(TIME_REGEX);
    }

    /**
     * Enumeration for lesson status.
     */
    public enum LessonStatus {
        COMPLETED, INCOMPLETE, LAPSED
    }

    /**
     * Gets the subject of the lesson.
     *
     * @return The subject.
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the date of the lesson.
     *
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time of the lesson.
     *
     * @return The time.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets the status of the lesson.
     *
     * @return The status.
     */
    public LessonStatus getStatus() {
        return status;
    }
    public void setLessonComplete() {
        this.status = LessonStatus.COMPLETED;
    }
    public void setLessonLapsed() {
        this.status = LessonStatus.LAPSED;
    }
    public void setLessonIncomplete() {
        this.status = LessonStatus.INCOMPLETE;
    }

    /**
     * Compares this lesson with another lesson based on their date and time.
     *
     * @param other The other lesson to compare with.
     * @return A negative integer, zero, or a positive integer as this lesson is before,
     *         at the same time, or after the other lesson.
     */
    @Override
    public int compareTo(Lesson other) {
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) {
            return dateComparison;
        }
        return this.time.compareTo(other.time);
    }
}
