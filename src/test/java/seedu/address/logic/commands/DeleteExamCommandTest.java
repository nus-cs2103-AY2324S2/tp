package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;

public class DeleteExamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Exam examToDelete = model.getExamList().get(INDEX_FIRST_EXAM.getZeroBased());
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);

        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_DELETE_EXAM_SUCCESS,
                Messages.format(examToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteExam(examToDelete);

        assertCommandSuccess(deleteExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getExamList().size() + 1);
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(outOfBoundIndex);

        assertCommandFailure(deleteExamCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExamCommand deleteFirstCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);
        DeleteExamCommand deleteSecondCommand = new DeleteExamCommand(INDEX_SECOND_EXAM);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteExamCommand deleteFirstCommandCopy = new DeleteExamCommand(INDEX_FIRST_EXAM);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different exam -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void execute_validIndex_removesExamFromPersons() {
        Exam examToDelete = model.getExamList().get(INDEX_FIRST_EXAM.getZeroBased());
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);

        boolean examExistsInPersons = model.getAddressBook().getPersonList().stream()
                                           .anyMatch(person -> person.getScores().containsKey(examToDelete));
        assertTrue(examExistsInPersons);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteExam(examToDelete);

        // Execute the command
        assertCommandSuccess(deleteExamCommand, model,
                             String.format(DeleteExamCommand.MESSAGE_DELETE_EXAM_SUCCESS,
                                           Messages.format(examToDelete)),
                             expectedModel);

        // Verify that the exam is removed from all persons
        for (Person person : expectedModel.getAddressBook().getPersonList()) {
            assertFalse(person.getScores().containsKey(examToDelete));
        }
    }

    @Test
    public void execute_selectedExam_setsSelectedExamToNull() throws CommandException {
        // Select an exam
        model.selectExam(model.getExamList().get(INDEX_FIRST_EXAM.getZeroBased()));
        assertEquals(model.getSelectedExam().getValue(),
                     model.getExamList().get(INDEX_FIRST_EXAM.getZeroBased()));
        // Delete the selected exam
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(INDEX_FIRST_EXAM);
        deleteExamCommand.execute(model);

        // Verify that the selected exam is set to null
        assertNull(model.getSelectedExam().getValue());
    }
}

