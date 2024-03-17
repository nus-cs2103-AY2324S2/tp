package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_CODE_AMY;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourse.getTypicalCourseName;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.*;
import seedu.address.model.course.Course;
import seedu.address.model.person.Person;

import java.nio.file.Path;
import java.util.function.Predicate;

public class SetCourseCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalCourseName());

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

    @Test
    public void constructor_courseName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetCourseCommand(null));
    }


}
