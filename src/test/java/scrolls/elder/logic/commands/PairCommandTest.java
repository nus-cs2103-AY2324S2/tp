package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Optional;

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

class PairCommandTest {

    @Test
    void execute_pairFilteredPersonList_pairSuccessful() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Person befriendeeToPair =
                model.getFilteredBefriendeeList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        Person volunteerToPair =
                model.getFilteredVolunteerList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased());
        PairCommand pairCommand =
                new PairCommand(TypicalIndexes.INDEX_SECOND_PERSON, TypicalIndexes.INDEX_SECOND_PERSON);

        String expectedMessage = String.format(PairCommand.MESSAGE_PAIR_SUCCESS,
                Messages.format(befriendeeToPair), Messages.format(volunteerToPair));

        Person afterPairingPerson1 = new PersonBuilder(befriendeeToPair)
                .withPairedWith(Optional.of(volunteerToPair.getName())).build();
        Person afterPairingPerson2 = new PersonBuilder(volunteerToPair)
                .withPairedWith(Optional.of(befriendeeToPair.getName())).build();

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(
                expectedModel.getFilteredBefriendeeList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased()),
                afterPairingPerson1);
        expectedModel.setPerson(
                expectedModel.getFilteredVolunteerList().get(TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased()),
                afterPairingPerson2);

        assertCommandSuccess(pairCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_alreadyPaired_throwsCommandException() {
        Model model = new ModelManager(new AddressBook(TypicalPersons.getTypicalAddressBook()), new UserPrefs());
        PairCommand pairCommand = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIRST_PERSON);
        Assert.assertThrows(
                CommandException.class, PairCommand.MESSAGE_ALREADY_PAIRED, () -> pairCommand.execute(model));
    }

    @Test
    public void equals() {
        PairCommand pairCommand1 = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        PairCommand pairCommand2 = new PairCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                TypicalIndexes.INDEX_FOURTH_PERSON);

        // same object -> returns true
        assertEquals(pairCommand1, pairCommand1);

        // same values -> returns true
        PairCommand pairCommand1Copy = new PairCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                TypicalIndexes.INDEX_FIFTH_PERSON);
        assertEquals(pairCommand1, pairCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, pairCommand1);

        // null -> returns false
        assertNotEquals(null, pairCommand1);

        // different person -> returns false
        assertNotEquals(pairCommand1, pairCommand2);
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
