package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_CS2103T;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.course.Course;

public class SetCourseCommandTest {

    @Test
    public void equals() {
        final SetCourseCommand standardCommand = new SetCourseCommand(new Course(VALID_COURSE_CODE_CS2101));

        // same values -> returns true
        SetCourseCommand commandWithSameValues = new SetCourseCommand(new Course(VALID_COURSE_CODE_CS2101));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different course_code -> returns false
        assertFalse(standardCommand.equals(new SetCourseCommand(new Course(VALID_COURSE_CODE_CS2103T))));
    }

    @Test
    public void constructor_courseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetCourseCommand(null));
    }


}
