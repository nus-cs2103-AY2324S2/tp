package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.AddressBook;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.model.person.Person;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalIndexes;
import scrolls.elder.testutil.TypicalPersons;

import java.util.Optional;

class UnpairCommandTest {

    /* To fix test case */
    @Test
    void execute_unpairFilteredPersonList_unpairSuccessful() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Person personToUnpair1 = model.getFilteredPersonList().get(TypicalIndexes.INDEX_EIGHTH_PERSON.getZeroBased());
        Person personToUnpair2 = model.getFilteredPersonList().get(TypicalIndexes.INDEX_NINTH_PERSON.getZeroBased());
        UnpairCommand unpairCommand =
                new UnpairCommand(TypicalIndexes.INDEX_EIGHTH_PERSON, TypicalIndexes.INDEX_NINTH_PERSON);

        String expectedMessage = String.format(UnpairCommand.MESSAGE_UNPAIR_SUCCESS,
                Messages.format(personToUnpair1), Messages.format(personToUnpair2));

        Person afterUnpairingPerson1 = new PersonBuilder(personToUnpair1).withPairedWith(Optional.empty()).build();
        Person afterUnpairingPerson2 = new PersonBuilder(personToUnpair2).withPairedWith(Optional.empty()).build();

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(TypicalIndexes.INDEX_EIGHTH_PERSON.getZeroBased()),
                afterUnpairingPerson1);
        expectedModel.setPerson(model.getFilteredPersonList().get(TypicalIndexes.INDEX_NINTH_PERSON.getZeroBased()),
                afterUnpairingPerson2);

        assertCommandSuccess(unpairCommand, model, expectedMessage, expectedModel);
    }


    @Test
    void execute_duplicatePerson_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        UnpairCommand unpairCommand =
                new UnpairCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_PERSON);
        Assert.assertThrows(
                CommandException.class, UnpairCommand.MESSAGE_DUPLICATE_PERSON, () -> unpairCommand.execute(model));
    }

    @Test
    void execute_contactsNotPaired_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        UnpairCommand unpairCommand = new UnpairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        Assert.assertThrows(
                CommandException.class, UnpairCommand.MESSAGE_NOT_PAIRED, () -> unpairCommand.execute(model));
    }

    @Test
    public void equals() {
        UnpairCommand unpairCommand1 = new UnpairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        UnpairCommand unpairCommand2 = new UnpairCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                TypicalIndexes.INDEX_FOURTH_PERSON);

        // same object -> returns true
        assertTrue(unpairCommand1.equals(unpairCommand1));

        // same values -> returns true
        UnpairCommand unpairCommand1Copy = new UnpairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        assertTrue(unpairCommand1.equals(unpairCommand1Copy));

        // different types -> returns false
        assertFalse(unpairCommand1.equals(1));

        // null -> returns false
        assertFalse(unpairCommand1.equals(null));

        // different person -> returns false
        assertFalse(unpairCommand1.equals(unpairCommand2));
    }

    @Test
    public void toStringMethod() {
        UnpairCommand unpairCommand =
                new UnpairCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIFTH_PERSON);
        String expected = UnpairCommand.class.getCanonicalName()
                + "{index1=" + TypicalIndexes.INDEX_FIRST_PERSON
                + ", index2=" + TypicalIndexes.INDEX_FIFTH_PERSON + "}";
        assertEquals(expected, unpairCommand.toString());
    }
}
