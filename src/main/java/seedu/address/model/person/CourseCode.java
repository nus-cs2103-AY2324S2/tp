package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Classes's Course Code in the class book.
 */
public class CourseCode {
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String MESSAGE_CONSTRAINTS = "Course codes should only contain alphanumeric characters "
            + "and no spaces, and it should not be blank";
    private String courseCode;

    /**
     * Constructor for CourseCode.
     *
     * @param courseCode A valid course code.
     */
    public CourseCode(String courseCode) {
        requireNonNull(courseCode);
        checkArgument(isValidClass(courseCode), MESSAGE_CONSTRAINTS);
        this.courseCode = courseCode;
    }

    /**
     * Returns true if a given string is a valid courseCode.
     */
    public static boolean isValidClass(String courseCode) {
        return courseCode.matches(VALIDATION_REGEX);
    }

    /**
     * Getter function that returns the course code.
     * @return course code
     */
    public String getCourseCode() {
        return courseCode;
    }
    @Override
    public String toString() {
        return courseCode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseCode)) {
            return false;
        }

        CourseCode otherCourseCode = (CourseCode) other;
        return courseCode.equals(otherCourseCode.courseCode);
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}
