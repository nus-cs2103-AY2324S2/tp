package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import seedu.address.logic.commands.EditExamCommand.EditExamDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;
import seedu.address.testutil.EditExamDescriptorBuilder;

public class EditExamCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Exam editedExam = new Exam("CA1", new Score(100));
        EditExamDescriptor descriptor = new EditExamDescriptorBuilder(editedExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST_EXAM, descriptor);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, Messages.format(editedExam));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setExam(model.getExamList().get(0), editedExam);

        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Exam editedExam = new Exam("CA1", new Score(100));
        EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withName("CA1").build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_FIRST_EXAM, descriptor);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_EDIT_EXAM_SUCCESS, Messages.format(editedExam));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setExam(model.getExamList().get(0), editedExam);

        assertCommandSuccess(editExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateExamUnfilteredList_failure() {
        Exam firstExam = model.getExamList().get(INDEX_FIRST_EXAM.getZeroBased());
        EditExamDescriptor descriptor = new EditExamDescriptorBuilder(firstExam).build();
        EditExamCommand editExamCommand = new EditExamCommand(INDEX_SECOND_EXAM, descriptor);

        assertCommandFailure(editExamCommand, model, EditExamCommand.MESSAGE_DUPLICATE_EXAM);
    }

    @Test
    public void execute_invalidExamIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getExamList().size() + 1);
        EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withName("CA1").build();
        EditExamCommand editExamCommand = new EditExamCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editExamCommand, model, Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        EditExamDescriptor descMidterm = new EditExamDescriptorBuilder().withName("Midterm")
                                                                         .withScore(new Score(100))
                                                                         .build();

        EditExamDescriptor descFinal = new EditExamDescriptorBuilder().withName("Final")
                                                                      .withScore(new Score(100))
                                                                      .build();

        final EditExamCommand standardCommand = new EditExamCommand(INDEX_FIRST_EXAM, descMidterm);

        // same values -> returns true
        EditExamDescriptor copyDescriptor = new EditExamDescriptor(descMidterm);

        EditExamCommand commandWithSameValues = new EditExamCommand(INDEX_FIRST_EXAM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(INDEX_SECOND_EXAM, descMidterm)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(INDEX_FIRST_EXAM, descFinal)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();
        EditExamCommand editExamCommand = new EditExamCommand(index, editExamDescriptor);
        String expected = EditExamCommand.class.getCanonicalName() + "{index=" + index + ", editExamDescriptor="
                + editExamDescriptor + "}";
        assertEquals(expected, editExamCommand.toString());
    }
}
