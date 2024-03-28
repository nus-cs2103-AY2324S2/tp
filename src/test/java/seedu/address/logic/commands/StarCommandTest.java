package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Star;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StarCommand.
 */
public class StarCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addStarUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student starredStudent = new StudentBuilder(firstStudent).withStar(8).build();

        StarCommand starCommand = new StarCommand(INDEX_FIRST_STUDENT, new Star(3));

        String expectedMessage = String.format(StarCommand.MESSAGE_ADD_STAR_SUCCESS, Messages.format(starredStudent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, starredStudent);

        assertCommandSuccess(starCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        StarCommand starCommand = new StarCommand(outOfBoundIndex, new Star(1));

        assertCommandFailure(starCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StarCommand starFirstCommand = new StarCommand(INDEX_FIRST_STUDENT, new Star(1));
        StarCommand starSecondCommand = new StarCommand(INDEX_SECOND_STUDENT, new Star(1));

        // same object -> returns true
        assertTrue(starFirstCommand.equals(starFirstCommand));

        // same values -> returns true
        StarCommand starFirstCommandCopy = new StarCommand(INDEX_FIRST_STUDENT, new Star(1));
        assertTrue(starFirstCommand.equals(starFirstCommandCopy));

        // different types -> returns false
        assertFalse(starFirstCommand.equals(1));

        // null -> returns false
        assertFalse(starFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(starFirstCommand.equals(starSecondCommand));
    }
}
