package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddNoteCommand.
 */
public class AddNoteCommandTest {
    @Test
    public void testReplaceNote() throws CommandException {
        // Setup
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person person = model.getFilteredPersonList().get(0);
        Note originalNote = person.getNote();
        AddNoteCommand command = new AddNoteCommand(
                new IdentityCardNumberMatchesPredicate(person.getIdentityCardNumber()),
                new Note("new note"), true);

        // Action
        command.execute(model);

        // Verify
        assertEquals(originalNote.toString(), person.getNote().toString());
    }

    @Test
    public void generateSuccessMessage_noteAdded_successMessage() {
        Person personToEdit = new PersonBuilder().withName("Alice").withIdentityCardNumber("S1234567A").build();
        String expectedMessage = String.format(AddNoteCommand.MESSAGE_MODIFY_NOTE_SUCCESS,
                personToEdit.getName(), personToEdit.getIdentityCardNumber());

        assertEquals(expectedMessage, new AddNoteCommand(
                new IdentityCardNumberMatchesPredicate(personToEdit.getIdentityCardNumber()),
                new Note("new note"), false).generateSuccessMessage(personToEdit));
    }

    @Test
    public void equals() {
        IdentityCardNumberMatchesPredicate firstPredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S1234567A"));
        IdentityCardNumberMatchesPredicate secondPredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S9876543B"));

        final AddNoteCommand standardCommand = new AddNoteCommand(firstPredicate,
                new Note(VALID_NOTE_AMY), false);

        // same values -> returns true
        AddNoteCommand commandWithSameValues = new AddNoteCommand(firstPredicate,
                new Note(VALID_NOTE_AMY), false);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(secondPredicate,
                new Note(VALID_NOTE_AMY), false)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(firstPredicate,
                new Note(VALID_NOTE_BOB), false)));
    }
}
