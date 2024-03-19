package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipCommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Remark;
import seedu.address.testutil.InternshipBuilder;

public class InternshipRemarkCommandTest {
    private static final String REMARK_STUB = "A remark";
    private InternshipModel model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(firstInternship).withRemark(REMARK_STUB).build();

        InternshipRemarkCommand remarkCommand = new InternshipRemarkCommand(INDEX_FIRST_INTERNSHIP,
                new Remark(editedInternship.getRemark().value));

        String expectedMessage = String.format(InternshipRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedInternship);

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(firstInternship).withRemark("").build();

        InternshipRemarkCommand remarkCommand = new InternshipRemarkCommand(INDEX_FIRST_INTERNSHIP,
                new Remark(editedInternship.getRemark().toString()));

        String expectedMessage = String.format(InternshipRemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedInternship);

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(model.getFilteredInternshipList()
                .get(INDEX_FIRST_INTERNSHIP.getZeroBased()))
                .withRemark(REMARK_STUB).build();

        InternshipRemarkCommand remarkCommand = new InternshipRemarkCommand(INDEX_FIRST_INTERNSHIP,
                new Remark(editedInternship.getRemark().value));

        String expectedMessage = String.format(InternshipRemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedInternship);

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(firstInternship, editedInternship);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        InternshipRemarkCommand remarkCommand = new InternshipRemarkCommand(outOfBoundIndex,
                new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of internship data
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship data list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipData().getInternshipList().size());

        InternshipRemarkCommand remarkCommand = new InternshipRemarkCommand(outOfBoundIndex,
                new Remark(VALID_REMARK_BOB));

        assertCommandFailure(remarkCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final InternshipRemarkCommand standardCommand = new InternshipRemarkCommand(INDEX_FIRST_INTERNSHIP,
                new Remark(VALID_REMARK_AMY));

        // same values -> returns true
        InternshipRemarkCommand commandWithSameValues = new InternshipRemarkCommand(INDEX_FIRST_INTERNSHIP,
                new Remark(VALID_REMARK_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new InternshipClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new InternshipRemarkCommand(INDEX_SECOND_INTERNSHIP,
                new Remark(VALID_REMARK_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new InternshipRemarkCommand(INDEX_FIRST_INTERNSHIP,
                new Remark(VALID_REMARK_BOB))));
    }
}
