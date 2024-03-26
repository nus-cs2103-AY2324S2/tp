package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.NON_EXISTENT_NRIC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReadCommand.MESSAGE_READ_PERSON_SUCCESS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Nric;

public class ReadCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_validNric_success() {
        String expectedMessage = String.format(MESSAGE_READ_PERSON_SUCCESS, Messages.formatRead(ALICE));
        ReadCommand command = new ReadCommand(ALICE.getNric());
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_nonExistentNric_failure() {
        Nric nonexistentNric = new Nric("S1234567A");
        assertCommandFailure(new ReadCommand(nonexistentNric), model, Messages.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    void equals() {
        ReadCommand readFirstCommand = new ReadCommand(ALICE.getNric());
        ReadCommand readSecondCommand = new ReadCommand(new Nric(NON_EXISTENT_NRIC));

        // same object -> returns true
        assertTrue(readFirstCommand.equals(readFirstCommand));

        // same values -> returns true
        ReadCommand readFirstCommandCopy = new ReadCommand(ALICE.getNric());
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
        ReadCommand readCommand = new ReadCommand(ALICE.getNric());
        String expected = ReadCommand.class.getCanonicalName() + "{nric=" + ALICE.getNric() + "}";
        assertEquals(expected, readCommand.toString());
    }

}
