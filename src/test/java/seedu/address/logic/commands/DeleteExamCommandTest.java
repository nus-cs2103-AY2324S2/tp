package seedu.address.logic.commands;

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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;

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
}
