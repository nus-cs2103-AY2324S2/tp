package seedu.address.model.course;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Course in the address book.
 * Holds details about a course, such as its code, which follows a specific format.
 * <p>
 * The course code is expected to adhere to the format defined by {@link #VALIDATION_REGEX},
 * which ensures it matches the typical NUS course code format. This format includes two
 * initial uppercase letters, followed by four digits, and an optional trailing uppercase letter.
 * </p>
 * <p>
 * Note: The course code is not marked as final to allow for the possibility of changes to
 * the course code after the course object has been created. This flexibility accommodates
 * scenarios where course codes might need to be updated or corrected.
 * </p>
 */
public class Course {
    public static final String COURSE_CODE_FORMAT = "XX1234Y";

    public static final String MESSAGE_CONSTRAINTS =
            "Course code should follow the format \"" + COURSE_CODE_FORMAT + "\", Y is optional";

    /**
     * Represents a course code validation in NUS Computer Science.
     * A valid course code must have two uppercase letters, followed by four digits,
     * and can optionally end with an uppercase letter.
     */
    public static final String VALIDATION_REGEX = "^[A-Z]{2}\\d{4}[A-Z]?$";

    private String courseCode;

    /**
     * Constructs a {@code Course}.
     *
     * @param code A valid course.
     */
    public Course(String code) {
        requireNonNull(code);

        courseCode = code;

    }

    /**
     * Checks if the given string matches the NUS course code format.
     *
     * @param test The string to test against the course code validation regex.
     * @return true if the given string matches the course code format, false otherwise.
     */
    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return courseCode;
    }

    /**
     * Changes the course code of this course to the specified code.
     * The new code provided must match the NUS Computer Science course code format.
     *
     * @param code The new course code to set, following the format "XX1234Y", where "Y" is optional.
     *             It must comply with {@link Course#VALIDATION_REGEX}.
     */
    public void changeCode(String code) {
        this.courseCode = code;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Course)) {
            return false;
        }

        Course otherCourse = (Course) other;
        return courseCode.equals(otherCourse.courseCode);
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }

    /**
     * Compares this Course's courseCode with another Course's courseCode for equality, ignoring case considerations.
     * <p>
     * This method provides a case-insensitive comparison of course codes, which can be useful for
     * ensuring consistency in comparisons regardless of how the course codes are capitalized.
     * </p>
     *
     * @param other The Course object to be compared with this Course for equality.
     * @return true if the course codes of both courses are equal irrespective of case; false otherwise.
     */
    public boolean equalsIgnoreCase(Course other) {
        if (other == this) {
            return true;
        }
        return this.courseCode.equalsIgnoreCase(other.courseCode);
    }

}
