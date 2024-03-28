package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class RedoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_multipleInvalidRedo_throwsCommandException() {
        RedoCommand redoCommand = new RedoCommand();

        String expectedMessage = RedoCommand.MESSAGE_REDO_EXCEPTION;

        assertCommandFailure(redoCommand, model, expectedMessage);
        assertCommandFailure(redoCommand, model, expectedMessage);
        assertCommandFailure(redoCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneRedo_success() {
        RedoCommand redoCommand = new RedoCommand();

        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);
        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.undo();
        assertEquals(model, expectedModel); // undid successfully

        expectedModel.deletePerson(personToDelete);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleRedo_success() {
        RedoCommand redoCommand = new RedoCommand();

        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;

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
        ModelManager expectedModelClear = new ModelManager(new AddressBook(), new UserPrefs());
        model.setAddressBook(new AddressBook());

        model.undo();
        model.undo();
        model.undo();

        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelDelete);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelEdit);
        assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelClear);
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_REDO_EXCEPTION);
    }

    @Test
    public void execute_modifyBeforeRedo_exception() {
        RedoCommand redoCommand = new RedoCommand();

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);
        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.undo();
        assertEquals(model, expectedModel); // undid successfully

        model.setAddressBook(new AddressBook()); // modify before redo
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_REDO_EXCEPTION);
    }

    @Test
    public void execute_interweaveUndoAndRedo_success() {
        RedoCommand redoCommand = new RedoCommand();

        String expectedMessage = RedoCommand.MESSAGE_SUCCESS;

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(personToDelete);
        ModelManager expectedModelBase = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ModelManager expectedModelDelete = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelDelete.deletePerson(personToDelete);

        // weaving undo & redo multiple times
        for (int i = 0; i < 5; i++) {
            model.undo();
            assertEquals(model, expectedModelBase); // undid successfully
            assertCommandSuccess(redoCommand, model, expectedMessage, expectedModelDelete);
        }
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_REDO_EXCEPTION);
    }

}
