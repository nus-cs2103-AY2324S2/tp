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
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Internship;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipAddDeadlineCommand.
 */
public class InternshipAddDeadlineCommandTest {
    private static final Index INDEX_FIRST_TASK = Index.fromOneBased(1);
    private static final Index INDEX_SECOND_TASK = Index.fromOneBased(2);
    private static final Deadline DEFAULT_DEADLINE = new Deadline("20/04/2024");
    private final InternshipModel model = new InternshipModelManager(getTypicalInternshipData(),
            new InternshipUserPrefs());

    @Test
    public void execute_internshipWithSpecifiedTaskAndDeadline_success() {
        //this is ALICE_MICROSOFT, cannot add deadline to it directly because the change will persist and affect
        //other tests
        Internship internshipWithAddedDeadline = getTypicalInternshipData().getInternshipList().get(0);
        internshipWithAddedDeadline.getTaskList().getTask(0).addDeadline(DEFAULT_DEADLINE);

        InternshipAddDeadlineCommand addDeadlineCommand = new InternshipAddDeadlineCommand(INDEX_FIRST_INTERNSHIP,
                INDEX_FIRST_TASK, DEFAULT_DEADLINE);

        String expectedMessage = String.format(InternshipAddDeadlineCommand.MESSAGE_ADD_DEADLINE_SUCCESS,
                InternshipMessages.format(internshipWithAddedDeadline));

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(getTypicalInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(expectedModel.getFilteredInternshipList().get(0), internshipWithAddedDeadline);

        assertCommandSuccess(addDeadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        InternshipAddDeadlineCommand addDeadlineCommand = new InternshipAddDeadlineCommand(outOfBoundIndex,
                INDEX_FIRST_TASK, DEFAULT_DEADLINE);

        assertCommandFailure(addDeadlineCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        Internship internshipWithTask = getTypicalInternships().get(0);
        Index outOfBoundIndex = Index.fromOneBased(internshipWithTask.getTaskListSize() + 1);
        InternshipAddDeadlineCommand addDeadlineCommand = new InternshipAddDeadlineCommand(INDEX_FIRST_INTERNSHIP,
                outOfBoundIndex, DEFAULT_DEADLINE);

        assertCommandFailure(addDeadlineCommand, model, InternshipMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final InternshipAddDeadlineCommand standardCommand = new
                InternshipAddDeadlineCommand(INDEX_FIRST_INTERNSHIP, INDEX_FIRST_TASK, DEFAULT_DEADLINE);

        // same values -> returns true
        InternshipAddDeadlineCommand commandWithSameValues = new InternshipAddDeadlineCommand(INDEX_FIRST_INTERNSHIP,
                INDEX_FIRST_TASK, DEFAULT_DEADLINE);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new InternshipClearCommand()));

        // different internship index -> returns false
        assertFalse(standardCommand.equals(new InternshipAddDeadlineCommand(INDEX_SECOND_INTERNSHIP, INDEX_FIRST_TASK,
                DEFAULT_DEADLINE)));

        // different task index -> returns false
        assertFalse(standardCommand.equals(new InternshipAddDeadlineCommand(INDEX_FIRST_INTERNSHIP, INDEX_SECOND_TASK,
                DEFAULT_DEADLINE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new InternshipAddDeadlineCommand(INDEX_FIRST_INTERNSHIP, INDEX_FIRST_TASK,
                new Deadline("22/02/2022"))));
    }

    @Test
    public void toStringMethod() {
        Index internshipIndex = Index.fromOneBased(1);
        Index taskIndex = Index.fromOneBased(1);
        InternshipAddDeadlineCommand addDeadlineCommand = new InternshipAddDeadlineCommand(internshipIndex, taskIndex,
                DEFAULT_DEADLINE);
        String expected = InternshipAddDeadlineCommand.class.getCanonicalName() + "{internshipIndex=" + internshipIndex
                + ", taskIndex=" + taskIndex + ", deadline=" + DEFAULT_DEADLINE + "}";
        assertEquals(expected, addDeadlineCommand.toString());
    }

}
