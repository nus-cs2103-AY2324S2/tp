package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Class in the class book.
 */
public class Classes {

    private CourseCode courseCode;

    /**
     * Constructor for Classes.
     */
    public Classes(CourseCode courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Getter function to return courseCode.
     *
     * @return courseCode
     */
    public CourseCode getCourseCode() {
        return courseCode;
    }

    /**
     * Returns true if both class have the same courseCode.
     */
    public boolean isSameClass(Classes otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null && otherClass.getCourseCode().equals(getCourseCode());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Classes)) {
            return false;
        }

        Classes otherClass = (Classes) other;
        return courseCode.equals(otherClass.courseCode);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("courseCode", courseCode)
                .toString();
    }

}
