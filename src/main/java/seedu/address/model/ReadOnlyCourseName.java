package seedu.address.model;

import seedu.address.model.course.Course;

/**
 * Unmodifiable view of a list of course (only 1 for now)
 */
public interface ReadOnlyCourseName {

    /**
     * Returns an unmodifiable view of the courses.
     * This list will not contain any duplicate courses, and only.
     * have one for now.
     */
    Course getCourse();

}
