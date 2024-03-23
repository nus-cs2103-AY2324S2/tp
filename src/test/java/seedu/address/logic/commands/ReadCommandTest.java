package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;

public class ReadCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /*@Test
    void execute_validNric_success() {

    }

    @Test
    void execute_invalidNric_failure() {

    }*/

    @Test
    void equals() {
        ReadCommand readFirstCommand = new ReadCommand(BOB.getNric());
        ReadCommand readSecondCommand = new ReadCommand(new Nric(NON_EXISTENT_NRIC));

        // same object -> returns true
        assertTrue(readFirstCommand.equals(readFirstCommand));

        // same values -> returns true
        ReadCommand readFirstCommandCopy = new ReadCommand(BOB.getNric());
        assertTrue(readFirstCommand.equals(readFirstCommandCopy));

        // different types -> returns false
        assertFalse(readFirstCommand.equals(1));

        // null -> returns false
        assertFalse(readFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(readFirstCommand.equals(readSecondCommand));
    }

    @Test
    void testToString() {
        ReadCommand readCommand = new ReadCommand(BOB.getNric());
        String expected = ReadCommand.class.getCanonicalName() + "{nric=" + BOB.getNric() + "}";
        assertEquals(expected, readCommand.toString());
    }

}
