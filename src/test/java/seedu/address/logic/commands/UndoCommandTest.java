package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_multipleInvalidUndo_throwsCommandException() {
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_EXCEPTION;

        assertCommandFailure(undoCommand, model, expectedMessage);
        assertCommandFailure(undoCommand, model, expectedMessage);
        assertCommandFailure(undoCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneUndo_success() {
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);
        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleUndo_success() {
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_SUCCESS;
        ModelManager expectedModelBase = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // delete
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ModelManager expectedModelDelete = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModelDelete.deletePerson(personToDelete);
        model.deletePerson(personToDelete);

        // edit
        Person editedPerson = new PersonBuilder().build();
        ModelManager expectedModelEdit = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModelEdit.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        editedPerson = new PersonBuilder().build();
        model.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        // clear
        model.setAddressBook(new AddressBook());

        // undo clear
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelEdit);
        // undo edit
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelDelete);
        // undo delete
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModelBase);
        // no more to undo
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_UNDO_EXCEPTION);
    }

}
