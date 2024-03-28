package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;
import static seedu.address.testutil.TypicalInternships.getTypicalInternships;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Task;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipAddTaskCommand.
 */
public class InternshipAddTaskCommandTest {
    private static final Task DEFAULT_TASK = new Task("edit resume");
    private final InternshipModel model = new InternshipModelManager(getTypicalInternshipData(),
            new InternshipUserPrefs());

    @Test
    public void execute_internshipWithSpecifiedTask_success() {
        Internship internshipWithAddedTask = getTypicalInternships().get(0);
        internshipWithAddedTask.addTask(DEFAULT_TASK);
        InternshipAddTaskCommand addTaskCommand = new InternshipAddTaskCommand(INDEX_FIRST_INTERNSHIP, DEFAULT_TASK);

        String expectedMessage = String.format(InternshipAddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                DEFAULT_TASK);

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), internshipWithAddedTask);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        InternshipAddTaskCommand addTaskCommand = new InternshipAddTaskCommand(outOfBoundIndex, DEFAULT_TASK);

        assertCommandFailure(addTaskCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final InternshipAddTaskCommand standardCommand = new
                InternshipAddTaskCommand(INDEX_FIRST_INTERNSHIP, DEFAULT_TASK);

        // same values -> returns true
        InternshipAddTaskCommand commandWithSameValues = new InternshipAddTaskCommand(INDEX_FIRST_INTERNSHIP,
                DEFAULT_TASK);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new InternshipClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new InternshipAddTaskCommand(INDEX_SECOND_INTERNSHIP, DEFAULT_TASK)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new InternshipAddTaskCommand(INDEX_FIRST_INTERNSHIP,
                new Task("finalise resume"))));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        InternshipAddTaskCommand addTaskCommand = new InternshipAddTaskCommand(index, DEFAULT_TASK);
        String expected = InternshipAddTaskCommand.class.getCanonicalName() + "{index=" + index
                + ", task=" + DEFAULT_TASK + "}";
        assertEquals(expected, addTaskCommand.toString());
    }
}
