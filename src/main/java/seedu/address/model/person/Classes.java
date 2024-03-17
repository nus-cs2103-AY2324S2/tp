package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;

import java.util.Objects;

public class Classes {

    private CourseCode courseCode;

    public Classes(CourseCode courseCode) {
        this.courseCode = courseCode;
    }

    public CourseCode getCourseCode() {
        return courseCode;
    }

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

        Classes otherClass= (Classes) other;
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
