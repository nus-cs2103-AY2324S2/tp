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
import seedu.address.model.person.Person;
import seedu.address.model.person.Star;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StarCommand.
 */
public class StarCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addStarUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person starredPerson = new PersonBuilder(firstPerson).withStar(8).build();

        StarCommand starCommand = new StarCommand(INDEX_FIRST_PERSON, new Star(3));

        String expectedMessage = String.format(StarCommand.MESSAGE_ADD_STAR_SUCCESS, Messages.format(starredPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, starredPerson);

        assertCommandSuccess(starCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        StarCommand starCommand = new StarCommand(outOfBoundIndex, new Star(1));

        assertCommandFailure(starCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StarCommand starFirstCommand = new StarCommand(INDEX_FIRST_PERSON, new Star(1));
        StarCommand starSecondCommand = new StarCommand(INDEX_SECOND_PERSON, new Star(1));

        // same object -> returns true
        assertTrue(starFirstCommand.equals(starFirstCommand));

        // same values -> returns true
        StarCommand starFirstCommandCopy = new StarCommand(INDEX_FIRST_PERSON, new Star(1));
        assertTrue(starFirstCommand.equals(starFirstCommandCopy));

        // different types -> returns false
        assertFalse(starFirstCommand.equals(1));

        // null -> returns false
        assertFalse(starFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(starFirstCommand.equals(starSecondCommand));
    }
}
