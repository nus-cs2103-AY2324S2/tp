package seedu.address.model.course;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


public class Course {
    public static final String MESSAGE_CONSTRAINTS =
            "Course code should follow the format \"XX1234Y\", Y is optional";

    /**
     * Represents a course code validation in NUS Computer Science.
     * A valid course code must have two uppercase letters, followed by four digits,
     * and can optionally end with an uppercase letter.
     */

    public static final String VALIDATION_REGEX = "^[A-Z]{2}\\d{4}[A-Z]?$";

    public String course_code;
    // Decided to not have the code as final above, as changes may occur.

    /**
     * Constructs a {@code Course}.
     *
     * @param code A valid course.
     */
    public Course(String code) {
        requireNonNull(code);

        course_code = code;

    }

    public static boolean isValidCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return course_code;
    }

    public void changeCode(String code) {
        this.course_code = code;
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
        return course_code.equals(otherCourse.course_code);
    }

    @Override
    public int hashCode() {
        return course_code.hashCode();
    }

}
