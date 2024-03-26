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
import seedu.address.model.student.Bolt;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BoltCommand.
 */
public class BoltCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addBoltUnfilteredList_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student boltredStudent = new StudentBuilder(firstStudent).withBolt(8).build();

        BoltCommand boltCommand = new BoltCommand(INDEX_FIRST_STUDENT, new Bolt(3));

        String expectedMessage = String.format(BoltCommand.MESSAGE_ADD_BOLT_SUCCESS, Messages.format(boltredStudent));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(firstStudent, boltredStudent);

        assertCommandSuccess(boltCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        BoltCommand boltCommand = new BoltCommand(outOfBoundIndex, new Bolt(1));

        assertCommandFailure(boltCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        BoltCommand boltFirstCommand = new BoltCommand(INDEX_FIRST_STUDENT, new Bolt(1));
        BoltCommand boltSecondCommand = new BoltCommand(INDEX_SECOND_STUDENT, new Bolt(1));

        // same object -> returns true
        assertTrue(boltFirstCommand.equals(boltFirstCommand));

        // same values -> returns true
        BoltCommand boltFirstCommandCopy = new BoltCommand(INDEX_FIRST_STUDENT, new Bolt(1));
        assertTrue(boltFirstCommand.equals(boltFirstCommandCopy));

        // different types -> returns false
        assertFalse(boltFirstCommand.equals(1));

        // null -> returns false
        assertFalse(boltFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(boltFirstCommand.equals(boltSecondCommand));
    }
}
