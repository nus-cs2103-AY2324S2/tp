package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
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
import seedu.address.model.person.Status;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StatusCommand.
 */
public class StatusCommandTest {

    private static final String STATUS_STUB = "ACCEPTED";
    private static final String VALID_STATUS = "PRESCREEN";
    private static final String ANOTHER_VALID_STATUS = "WAITLIST";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_updateStatusUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withStatus(STATUS_STUB).build();

        StatusCommand statusCommand = new StatusCommand(INDEX_FIRST_PERSON,
                new Status(editedPerson.getStatus().value));

        String expectedMessage = String.format(StatusCommand.MESSAGE_STATUS_PERSON_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased(), STATUS_STUB);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(statusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())).withStatus(STATUS_STUB).build();

        StatusCommand statusCommand = new StatusCommand(INDEX_FIRST_PERSON,
                new Status(editedPerson.getStatus().value));
        String expectedMessage = String.format(StatusCommand.MESSAGE_STATUS_PERSON_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased(), STATUS_STUB);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(statusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        StatusCommand statusCommand = new StatusCommand(outOfBoundIndex, new Status(VALID_STATUS));

        assertCommandFailure(statusCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        StatusCommand statusCommand = new StatusCommand(outOfBoundIndex, new Status(ANOTHER_VALID_STATUS));

        assertCommandFailure(statusCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final StatusCommand standardCommand = new StatusCommand(INDEX_FIRST_PERSON,
                new Status(VALID_STATUS));
        // same values -> returns true
        StatusCommand commandWithSameStatus = new StatusCommand(INDEX_FIRST_PERSON,
                new Status(VALID_STATUS));
        assertTrue(standardCommand.equals(commandWithSameStatus));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new StatusCommand(INDEX_SECOND_PERSON,
                new Status(VALID_STATUS))));
        // different comment -> returns false
        assertFalse(standardCommand.equals(new StatusCommand(INDEX_FIRST_PERSON,
                new Status(ANOTHER_VALID_STATUS))));
    }
}
