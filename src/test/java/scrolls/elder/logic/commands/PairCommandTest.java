package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.TypicalIndexes;
import scrolls.elder.testutil.TypicalPersons;

class PairCommandTest {

    /* Fix testcases such that it does not pollute JSON data
    @Test
    void execute_pairFilteredPersonList_pairSuccessful() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Person personToPair1 = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        Person personToPair2 = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIFTH_PERSON.getZeroBased());
        PairCommand pairCommand = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIFTH_PERSON);

        String expectedMessage = String.format(PairCommand.MESSAGE_PAIR_SUCCESS,
                Messages.format(personToPair1), Messages.format(personToPair2));

        Person afterPairingPerson1 = new PersonBuilder(personToPair1)
                .withPairedWith(Optional.of(personToPair2.getName())).build();
        Person afterPairingPerson2 = new PersonBuilder(personToPair2)
                .withPairedWith(Optional.of(personToPair1.getName())).build();

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(
                expectedModel.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased()),
                afterPairingPerson1);
        expectedModel.setPerson(
                expectedModel.getFilteredPersonList().get(TypicalIndexes.INDEX_FIFTH_PERSON.getZeroBased()),
                afterPairingPerson2);

        assertCommandSuccess(pairCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_alreadyPaired_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(TypicalPersons.getTypicalAddressBook()), new UserPrefs());
        PairCommand pairCommand = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        Assert.assertThrows(
                CommandException.class, PairCommand.MESSAGE_ALREADY_PAIRED, () -> pairCommand.execute(model));
    }
    */
    @Test
    void execute_duplicatePerson_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        PairCommand pairCommand = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIRST_PERSON);
        Assert.assertThrows(
                CommandException.class, PairCommand.MESSAGE_DUPLICATE_PERSON, () -> pairCommand.execute(model));
    }

    @Test
    void execute_samePersonType_throwsCommandException() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        PairCommand pairCommand = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_SECOND_PERSON);
        Assert.assertThrows(
                CommandException.class, PairCommand.MESSAGE_DIFFERENT_PERSON_TYPE, () -> pairCommand.execute(model));
    }

    @Test
    public void equals() {
        PairCommand pairCommand1 = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        PairCommand pairCommand2 = new PairCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                TypicalIndexes.INDEX_FOURTH_PERSON);

        // same object -> returns true
        assertTrue(pairCommand1.equals(pairCommand1));

        // same values -> returns true
        PairCommand pairCommand1Copy = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        assertTrue(pairCommand1.equals(pairCommand1Copy));

        // different types -> returns false
        assertFalse(pairCommand1.equals(1));

        // null -> returns false
        assertFalse(pairCommand1.equals(null));

        // different person -> returns false
        assertFalse(pairCommand1.equals(pairCommand2));
    }

    @Test
    public void toStringMethod() {
        PairCommand pairCommand = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON, TypicalIndexes.INDEX_FIFTH_PERSON);
        String expected = PairCommand.class.getCanonicalName()
                + "{index1=" + TypicalIndexes.INDEX_FIRST_PERSON
                + ", index2=" + TypicalIndexes.INDEX_FIFTH_PERSON + "}";
        assertEquals(expected, pairCommand.toString());
    }
}
