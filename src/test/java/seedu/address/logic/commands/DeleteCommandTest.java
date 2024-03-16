package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertRecentlyProcessedCourseMateEdited;
import static seedu.address.logic.commands.CommandTestUtil.showCourseMateAtIndex;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COURSE_MATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COURSE_MATE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.CourseMate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        CourseMate courseMateToDelete = model.getFilteredCourseMateList().get(INDEX_FIRST_COURSE_MATE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_COURSE_MATE);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_COURSE_MATE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getContactList(), new UserPrefs());
        expectedModel.deleteCourseMate(courseMateToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, courseMateToDelete);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCourseMateList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);

        CourseMate courseMateToDelete = model.getFilteredCourseMateList().get(INDEX_FIRST_COURSE_MATE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_COURSE_MATE);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_COURSE_MATE_SUCCESS;

        Model expectedModel = new ModelManager(model.getContactList(), new UserPrefs());
        expectedModel.deleteCourseMate(courseMateToDelete);
        showNoCourseMate(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel, true);
        assertRecentlyProcessedCourseMateEdited(model, courseMateToDelete);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCourseMateAtIndex(model, INDEX_FIRST_COURSE_MATE);

        Index outOfBoundIndex = INDEX_SECOND_COURSE_MATE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getCourseMateList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        assertRecentlyProcessedCourseMateEdited(model, null);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_COURSE_MATE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_COURSE_MATE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_COURSE_MATE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different courseMate -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCourseMate(Model model) {
        model.updateFilteredCourseMateList(p -> false);

        assertTrue(model.getFilteredCourseMateList().isEmpty());
    }
}
