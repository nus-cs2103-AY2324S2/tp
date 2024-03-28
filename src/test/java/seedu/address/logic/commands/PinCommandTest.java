package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for PinCommand.
 */
public class PinCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNusIdFilteredList_success() {
        Person personToPin = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        NusId nusId = new NusId("E0123456"); // This is the supposed NusId of the first person in the address list
        PinCommand pinCommand = new PinCommand(nusId);

        String expectedMessage = String.format(PinCommand.MESSAGE_PIN_PERSON_SUCCESS,
                Messages.format(personToPin));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.pinPerson(personToPin);

        assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNusIdFilteredList_throwsCommandException() {
        NusId testNusId = new NusId("E1234567");
        PinCommand pinCommand = new PinCommand(testNusId);
        assertCommandFailure(pinCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON);
    }

    @Test
    public void equals() {
        NusId nusIdForFirstDeleteCommand = new NusId("E1234567");
        NusId nusIdForSecondDeleteCommand = new NusId("E2345678");
        PinCommand pinFirstCommand = new PinCommand(nusIdForFirstDeleteCommand);
        PinCommand pinSecondCommand = new PinCommand(nusIdForSecondDeleteCommand);

        // same object -> returns true
        assertTrue(pinFirstCommand.equals(pinFirstCommand));

        // same values -> return true
        PinCommand pinFirstCommandCopy = new PinCommand(new NusId("E1234567"));
        assertTrue(pinFirstCommand.equals(pinFirstCommandCopy));

        // different types -> returns false
        assertFalse(pinFirstCommand.equals(1));

        // null -> returns false
        assertFalse(pinFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(pinFirstCommand.equals(pinSecondCommand));

    }

    @Test
    public void toStringMethod() {
        NusId testNusId = new NusId("E1234567");
        PinCommand pinCommand = new PinCommand(testNusId);
        String expected = PinCommand.class.getCanonicalName() + "{targetnusId=" + testNusId.toString() + "}";
        assertEquals(expected, pinCommand.toString());
    }

}
