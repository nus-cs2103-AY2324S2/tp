package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RedoMessages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RedoCommandTest {

    @Test
    public void execute_redoSuccessful() throws Exception {
        Model modelManager = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
        Person validPerson = new PersonBuilder().build();

        new AddCommand(validPerson).execute(modelManager);
        new UndoCommand().execute(modelManager);
        RedoCommand redoCommand = new RedoCommand();
        CommandResult redoCommandResult = redoCommand.execute(modelManager);
        assertEquals(RedoMessages.MESSAGE_REDO_SUCCESS, redoCommandResult.getFeedbackToUser());
    }

    @Test
    public void execute_impossibleRedoState_throwsCommandException() {
        Model modelManager = new ModelManager(new AddressBook(getTypicalAddressBook()), new UserPrefs());
        RedoCommand redoCommand = new RedoCommand();

        assertThrows(CommandException.class,
                RedoMessages.MESSAGE_REDO_FAIL, () -> redoCommand.execute(modelManager));
    }

    @Test
    public void equals() {
        RedoCommand firstRedoCommand = new RedoCommand();
        RedoCommand secondRedoCommand = new RedoCommand();

        // same object -> returns true
        assertTrue(firstRedoCommand.equals(secondRedoCommand));

        // different types -> returns false
        assertFalse(firstRedoCommand.equals(1));

        // null -> returns false
        assertFalse(firstRedoCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        RedoCommand redoCommand = new RedoCommand();
        String expected = RedoCommand.class.getCanonicalName() + "{}";
        assertEquals(expected, redoCommand.toString());
    }

}
