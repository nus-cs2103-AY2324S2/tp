package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_MOBILE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PayCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Person personToPay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PayCommand payCommand = new PayCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PayCommand.MESSAGE_GENERATE_QR_SUCCESS,
                Messages.format(personToPay));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(payCommand, model, new CommandResult(expectedMessage, personToPay), expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PayCommand payCommand = new PayCommand(outOfBoundIndex);

        assertCommandFailure(payCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PayCommand payCommand = new PayCommand(outOfBoundIndex);

        assertCommandFailure(payCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nonSingaporeanNumber_throwsCommandException() {
        final String[] invalidNumbers = new String[]{"72478212",
                                                     "82133",
                                                     "934234"};
        AddressBook addressBook = new AddressBook();
        addressBook.addPerson(new PersonBuilder(AMY).withPhone(invalidNumbers[0]).build());
        addressBook.addPerson(new PersonBuilder(BOB).withPhone(invalidNumbers[1]).build());
        addressBook.addPerson(new PersonBuilder(CARL).withPhone(invalidNumbers[2]).build());

        Model model = new ModelManager(addressBook, new UserPrefs());

        assertCommandFailure(new PayCommand(INDEX_FIRST_PERSON), model, MESSAGE_INVALID_MOBILE);
        assertCommandFailure(new PayCommand(INDEX_SECOND_PERSON), model, MESSAGE_INVALID_MOBILE);
        assertCommandFailure(new PayCommand(INDEX_THIRD_PERSON), model, MESSAGE_INVALID_MOBILE);
    }

    @Test
    public void equals() {
        PayCommand payFirstCommand = new PayCommand(INDEX_FIRST_PERSON);
        PayCommand paySecondCommand = new PayCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(payFirstCommand, payFirstCommand);

        // same values -> returns true
        PayCommand payFirstCommandCopy = new PayCommand(INDEX_FIRST_PERSON);
        assertEquals(payFirstCommand, payFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(payFirstCommand, 1);

        // null -> returns false
        assertNotEquals(payFirstCommand, null);

        // different person -> returns false
        assertNotEquals(payFirstCommand, paySecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        PayCommand payCommand = new PayCommand(targetIndex);
        String expected = PayCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, payCommand.toString());
    }
}
