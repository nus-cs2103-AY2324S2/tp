package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;
import seedu.address.testutil.EditNoteDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditNoteCommand.
 */
class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Note editedNote = new Note(LocalDateTime.now(), new Description("Updated Description"));
        executeCommand_success(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, editedNote,
                String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, Messages.format(editedNote)));
    }

    @Test
    public void execute_editDescriptionOnly_success() throws CommandException {
        Note originalNote = getOriginalNote(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE);
        Note editedNote = new Note(originalNote.getDateTime(), new Description("New Description Only"));
        executeCommand_success(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, editedNote,
                String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, Messages.format(editedNote)));
    }

    @Test
    public void execute_editDateTimeOnly_success() throws CommandException {
        Note originalNote = getOriginalNote(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE);
        Note editedNote = new Note(LocalDateTime.now(), originalNote.getDescription());
        executeCommand_success(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, editedNote,
                String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, Messages.format(editedNote)));
    }

    @Test
    public void execute_invalidPatientIndex_throwsCommandException() {
        Index outOfBoundPatientIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Note editedNote = new Note(LocalDateTime.now(), new Description("Doesn't matter"));
        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder(editedNote).build();
        EditNoteCommand command = new EditNoteCommand(outOfBoundPatientIndex, INDEX_FIRST_NOTE, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void execute_invalidNoteIndex_throwsCommandException() {
        Index patientIndex = INDEX_FIRST_PERSON;
        Index outOfBoundNoteIndex = Index.fromOneBased(model.getFilteredPersonList()
                .get(patientIndex.getZeroBased()).getNotes().size() + 1);
        Note editedNote = new Note(LocalDateTime.now(), new Description("Doesn't matter"));
        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder(editedNote).build();
        EditNoteCommand command = new EditNoteCommand(patientIndex, outOfBoundNoteIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void getCommandWord() {
        Command command = new EditNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, DESC_NOTE1);
        assertEquals(EditNoteCommand.COMMAND_WORD, command.getCommandWord());
    }

    @Test
    public void getMessageUsage() {
        Command command = new EditNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, DESC_NOTE1);
        assertEquals(EditNoteCommand.MESSAGE_USAGE, command.getMessageUsage());
    }

    @Test
    public void equals() {
        final EditNoteCommand standardCommand = new EditNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, DESC_NOTE1);
        EditNoteCommand.EditNoteDescriptor copyDescriptor = new EditNoteCommand.EditNoteDescriptor(DESC_NOTE1);
        EditNoteCommand commandWithSameValues = new EditNoteCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_NOTE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_SECOND_PERSON, INDEX_FIRST_NOTE, DESC_NOTE1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, DESC_NOTE2)));
    }

    @Test
    public void toStringMethod() {
        EditNoteCommand.EditNoteDescriptor editNoteDescriptor = new EditNoteCommand.EditNoteDescriptor();
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_PERSON, INDEX_FIRST_NOTE, editNoteDescriptor);
        String expected = EditNoteCommand.class.getCanonicalName() + "{patientIndex=" + INDEX_FIRST_PERSON
                + ", noteIndex=" + INDEX_FIRST_NOTE
                + ", editNoteDescriptor=" + editNoteDescriptor + "}";
        assertEquals(expected, editNoteCommand.toString());
    }

    private Note getOriginalNote(Index patientIndex, Index noteIndex) {
        return model.getFilteredPersonList().get(patientIndex.getZeroBased()).getNotes().get(noteIndex.getZeroBased());
    }

    private void executeCommand_success(Index patientIndex, Index noteIndex, Note editedNote,
                                     String expectedMessage) throws CommandException {
        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder(editedNote).build();
        EditNoteCommand command = new EditNoteCommand(patientIndex, noteIndex, descriptor);

        Person personToEdit = model.getFilteredPersonList().get(patientIndex.getZeroBased());
        ObservableList<Note> updatedNotes = FXCollections.observableArrayList(personToEdit.getNotes());
        updatedNotes.set(noteIndex.getZeroBased(), editedNote);
        Person updatedPerson = new Person.Builder(personToEdit).setNotes(updatedNotes).build();

        Model expectedModel = setupExpectedModel(personToEdit, updatedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    private Model setupExpectedModel(Person originalPerson, Person updatedPerson) {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(originalPerson, updatedPerson);
        return expectedModel;
    }
}
