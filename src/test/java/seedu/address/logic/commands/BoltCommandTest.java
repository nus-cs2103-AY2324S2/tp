package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Bolt;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BoltCommand.
 */
public class BoltCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addBoltUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person boltredPerson = new PersonBuilder(firstPerson).withBolt(8).build();

        BoltCommand boltCommand = new BoltCommand(INDEX_FIRST_PERSON, new Bolt(3));

        String expectedMessage = String.format(BoltCommand.MESSAGE_ADD_BOLT_SUCCESS, Messages.format(boltredPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, boltredPerson);

        assertCommandSuccess(boltCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BoltCommand boltCommand = new BoltCommand(outOfBoundIndex, new Bolt(1));

        assertCommandFailure(boltCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        BoltCommand boltFirstCommand = new BoltCommand(INDEX_FIRST_PERSON, new Bolt(1));
        BoltCommand boltSecondCommand = new BoltCommand(INDEX_SECOND_PERSON, new Bolt(1));

        // same object -> returns true
        assertTrue(boltFirstCommand.equals(boltFirstCommand));

        // same values -> returns true
        BoltCommand boltFirstCommandCopy = new BoltCommand(INDEX_FIRST_PERSON, new Bolt(1));
        assertTrue(boltFirstCommand.equals(boltFirstCommandCopy));

        // different types -> returns false
        assertFalse(boltFirstCommand.equals(1));

        // null -> returns false
        assertFalse(boltFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(boltFirstCommand.equals(boltSecondCommand));
    }
}
