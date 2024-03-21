package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a lesson in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    public static final String MESSAGE_CONSTRAINTS =
            "Lessons must be of the form subject|dd-MM-yyyy|hh:mm|0/1, where subject contains only alphabeths"
                    + " and spaces, and indicate lesson incomplete/completed with 0 or 1 respectively.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z][a-zA-Z ]*$";
    public static final String DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
    public static final String TIME_REGEX = "\\d{2}:\\d{2}";
    public static final String INT_REGEX = "[01]";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private String value;
    private final Subject subject;
    private final LocalDate date;
    private final LocalTime time;
    private int isCompleted;

    /**
     * Constructs a Lesson with the specified subject, date, and time.
     *
     * @param lesson The details of the lesson.
     */
    public Lesson(String lesson) {
        requireNonNull(lesson);
        // split lesson into its attributes based on "|" character
        String[] lessonDetails = lesson.trim().split("\\|");
        // throws IllegalArgumentException if the lesson is not in the correct format.
        if (lessonDetails.length != 4) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        String subjectDetail = lessonDetails[0];
        String dateDetail = lessonDetails[1];
        String timeDetail = lessonDetails[2];
        int isCompletedDetail = Integer.parseInt(lessonDetails[3]);
        // assign the attributes to the lesson
        this.subject = new Subject(subjectDetail);
        this.date = LocalDate.parse(dateDetail, dateFormatter);
        this.time = LocalTime.parse(timeDetail, timeFormatter);
        this.isCompleted = isCompletedDetail;
        // parseable form of lesson (temporary)
        this.value = subjectDetail + "|" + dateDetail + "|" + timeDetail + "|" + isCompleted;
    }

    /**
     * Returns true if a given string is a valid lesson.
     */
    public static boolean isValidLesson(String lessonValue) {
        String[] lessonDetails = lessonValue.split("\\|");
        if (lessonDetails.length != 4) {
            return false;
        }
        try {
            LocalDate.parse(lessonDetails[1], dateFormatter);
            LocalTime.parse(lessonDetails[2], timeFormatter);
            Integer.parseInt(lessonDetails[3]);
        } catch (DateTimeParseException | NumberFormatException e) {
            return false;
        }
        return lessonDetails[0].matches(VALIDATION_REGEX)
                && lessonDetails[1].matches(DATE_REGEX)
                && lessonDetails[2].matches(TIME_REGEX)
                && lessonDetails[3].matches(INT_REGEX);
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
    public int getLessonStatus() {
        return isCompleted;
    }

    public String getLessonValue() {
        return value;
    }

    public void setLessonComplete() {
        this.isCompleted = 1;
    }

    public void setLessonIncomplete() {
        this.isCompleted = 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return value.equals(otherLesson.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }
}
