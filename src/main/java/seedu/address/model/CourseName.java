package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.course.Course;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class CourseName implements ReadOnlyCourseName {

    private Course course;


    public CourseName() {}

    /**
     * Creates a Course using the course in the {@code toBeCopied}
     */
    public CourseName(ReadOnlyCourseName toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the course.
     */
    public void setCourse(Course c) {
        this.course = c;
    }

    /**
     * Resets the existing data of this {@code CourseName} with {@code newData}.
     */
    public void resetData(ReadOnlyCourseName newData) {
        requireNonNull(newData);

        this.course = newData.getCourse();
    }



    //// util methods

    @Override
    public String toString() {
        return course.toString();
    }

    @Override
    public Course getCourse() {
        return this.course;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CourseName)) {
            return false;
        }

        CourseName otherCourseName = (CourseName) other;
        return course.equals(otherCourseName.course);
    }

    @Override
    public int hashCode() {
        return course.hashCode();
    }
}
