package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddNoteCommand.
 */
public class AddNoteCommandTest {
    private static final String NOTE_STUB = "Some note";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withNote(NOTE_STUB).build();

        IdentityCardNumberMatchesPredicate firstpredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber(firstPerson.getIdentityCardNumber().toString()));

        AddNoteCommand addNoteCommand = new AddNoteCommand(firstpredicate, new Note(editedPerson.getNote().value), false);

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withNote("").build();

        IdentityCardNumberMatchesPredicate firstpredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber(firstPerson.getIdentityCardNumber().toString()));

        AddNoteCommand remarkCommand = new AddNoteCommand(firstpredicate,
                new Note(editedPerson.getNote().toString()), false);

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withNote(NOTE_STUB).build();

        IdentityCardNumberMatchesPredicate firstpredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber(firstPerson.getIdentityCardNumber().toString()));

        AddNoteCommand remarkCommand = new AddNoteCommand(firstpredicate, new Note(editedPerson.getNote().value), false);

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
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
