package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.UndoMessages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class UndoCommandTest {

    @Test
    public void execute_undoSuccessful() throws Exception {
        Model modelManager = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
        Person validPerson = new PersonBuilder().build();

        new AddCommand(validPerson).execute(modelManager);
        UndoCommand undoCommand = new UndoCommand();

        CommandResult undoCommandResult = undoCommand.execute(modelManager);
        assertEquals(UndoMessages.MESSAGE_UNDO_SUCCESS, undoCommandResult.getFeedbackToUser());
    }

    @Test
    public void execute_impossibleUndoState_throwsCommandException() {
        Model modelManager = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();

        assertThrows(CommandException.class,
                UndoMessages.MESSAGE_UNDO_FAIL, () -> undoCommand.execute(modelManager));
    }

    @Test
    public void equals() {
        UndoCommand firstUndoCommand = new UndoCommand();
        UndoCommand secondUndoCommand = new UndoCommand();

        // same object -> returns true
        assertTrue(firstUndoCommand.equals(secondUndoCommand));

        // different types -> returns false
        assertFalse(firstUndoCommand.equals(1));

        // null -> returns false
        assertFalse(firstUndoCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        UndoCommand undoCommand = new UndoCommand();
        String expected = UndoCommand.class.getCanonicalName() + "{}";
        assertEquals(expected, undoCommand.toString());
    }

}
