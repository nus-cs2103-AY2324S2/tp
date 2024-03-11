package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Points;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddPointsCommand.
 */
public class AddPointsCommandTest {

    private static final String POINTS_STUB = "20";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addPointsUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPoints(POINTS_STUB).build();

        AddPointsCommand addPointsCommand = new AddPointsCommand(firstPerson.getName(), new Points(POINTS_STUB));

        String expectedMessage = String.format(AddPointsCommand.MESSAGE_ADDPOINTS_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(addPointsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        Name outOfBoundName = new Name("Nonexistent Name");
        AddPointsCommand addPointsCommand = new AddPointsCommand(outOfBoundName, new Points(POINTS_STUB));

        assertCommandFailure(addPointsCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final Name firstPersonName = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName();
        final AddPointsCommand standardCommand = new AddPointsCommand(firstPersonName, new Points(POINTS_STUB));

        // same values -> returns true
        AddPointsCommand commandWithSameValues = new AddPointsCommand(firstPersonName, new Points(POINTS_STUB));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different name -> returns false
        Name secondPersonName = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()).getName();
        assertFalse(standardCommand.equals(new AddPointsCommand(secondPersonName, new Points(POINTS_STUB))));

        // different points -> returns false
        assertFalse(standardCommand.equals(new AddPointsCommand(firstPersonName, new Points("30"))));
    }
}
