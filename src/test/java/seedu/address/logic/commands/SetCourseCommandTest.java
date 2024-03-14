package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_AMY;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.SetCourseCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.course.Course;

public class SetCourseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        Course course = new Course("CS2103T");
        assertCommandFailure(new SetCourseCommand(course), model, "CS2103T" );
    }

    @Test
    public void equals() {
        final SetCourseCommand standardCommand = new SetCourseCommand(new Course(VALID_COURSE_CODE_AMY));

        // same values -> returns true
        SetCourseCommand commandWithSameValues = new SetCourseCommand(new Course(VALID_COURSE_CODE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different course_code -> returns false
        assertFalse(standardCommand.equals(new SetCourseCommand(new Course(VALID_COURSE_CODE_BOB))));
    }
}
