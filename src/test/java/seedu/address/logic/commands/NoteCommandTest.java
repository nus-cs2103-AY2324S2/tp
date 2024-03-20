package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_N;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_N;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STARTUP;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.NoteCommand.NoteStartupDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.NoteStartupDescriptorBuilder;
import seedu.address.testutil.StartupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NoteCommand.
 */
public class NoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noteFieldSpecifiedUnfilteredList_success() {
        // Setup
        Startup startupToEdit = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        Note updatedNote = new Note("Updated note content");
        NoteStartupDescriptor descriptor = new NoteStartupDescriptorBuilder().withNote(updatedNote.toString()).build();
        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_STARTUP, descriptor);

        // Expected outcome
        Startup expectedStartup = new StartupBuilder(startupToEdit).withNote(updatedNote.toString()).build();
        String expectedMessage = String.format(NoteCommand.MESSAGE_EDIT_STARTUP_SUCCESS,
                Messages.format(expectedStartup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStartup(startupToEdit, expectedStartup);

        // Assert command success
        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStartupIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStartupList().size() + 1);
        NoteStartupDescriptor descriptor = new NoteStartupDescriptorBuilder().withNote("Some note").build();
        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final NoteCommand standardCommand = new NoteCommand(INDEX_FIRST_STARTUP, DESC_AMY_N);

        // same values -> returns true
        NoteStartupDescriptor copyDescriptor = new NoteStartupDescriptor(DESC_AMY_N);
        NoteCommand commandWithSameValues = new NoteCommand(INDEX_FIRST_STARTUP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_SECOND_STARTUP, DESC_AMY_N)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(INDEX_FIRST_STARTUP, DESC_BOB_N)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        NoteStartupDescriptor noteStartupDescriptor = new NoteStartupDescriptor();
        NoteCommand noteCommand = new NoteCommand(index, noteStartupDescriptor);
        String expected = NoteCommand.class.getCanonicalName() + "{index=" + index + ", noteStartupDescriptor="
                + noteStartupDescriptor + "}";
        assertEquals(expected, noteCommand.toString());
    }

}
