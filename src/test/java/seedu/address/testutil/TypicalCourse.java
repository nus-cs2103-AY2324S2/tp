package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_BOB;

import seedu.address.model.CourseName;
import seedu.address.model.course.Course;

/**
 * A utility class containing a {@code Course} object to be used in tests.
 */
public class TypicalCourse {

    private TypicalCourse() {}

    public static CourseName getTypicalCourseName() {
        CourseName courseName = new CourseName();
        courseName.setCourse(new Course(VALID_COURSE_CODE_BOB));
        return courseName;
    }

    public static Course getTypicalCourse() {
        return new Course(VALID_COURSE_CODE_BOB);
    }




}
