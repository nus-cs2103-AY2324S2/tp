package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.RelationshipContainsKeywordsPredicate;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClientStatusCommand.
 */
public class ClientStatusCommandTest {

    private static final String CLIENT_STATUS_UP_STUB = "up";
    private static final String CLIENT_STATUS_DOWN_STUB = "down";
    private static final String CLIENT_STATUS_RESET_STUB = "";
    private static final String CLIENT_STATUS_INVALID_STUB = "invalid";
    private static final String CLIENT_STATUS_MID_STUB = "2";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_changeNonClientStatus_failure() {
        Predicate<Person> predicate =
                new RelationshipContainsKeywordsPredicate(Collections.singletonList("partner"));
        model.updateFilteredPersonList(predicate);

        ClientStatusCommand clientStatusCommand = new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_UP_STUB);

        assertCommandFailure(clientStatusCommand, model, ClientStatusCommand.MESSAGE_PERSON_NOT_CLIENT_FAILURE);
    }

    @Test
    public void execute_incrementClientStatusUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withClientStatus(CLIENT_STATUS_MID_STUB).build();

        ClientStatusCommand clientStatusCommand =
                new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_UP_STUB);

        String expectedMessage =
                String.format(ClientStatusCommand.MESSAGE_STATUS_UP_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(clientStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_decrementStatusUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withClientStatus("1").build();

        ClientStatusCommand clientStatusCommand =
                new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_DOWN_STUB);

        String expectedMessage =
                String.format(ClientStatusCommand.MESSAGE_STATUS_DOWN_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(clientStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_resetStatusUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withClientStatus("1").build();

        ClientStatusCommand clientStatusCommand =
                new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_RESET_STUB);

        String expectedMessage =
                String.format(ClientStatusCommand.MESSAGE_STATUS_RESET_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(clientStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDirectionUnfilteredList_failure() {
        ClientStatusCommand clientStatusCommand =
                new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_INVALID_STUB);

        assertCommandFailure(clientStatusCommand, model, ClientStatusCommand.MESSAGE_PERSON_INVALID_DIRECTION_FAILURE);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withClientStatus(CLIENT_STATUS_MID_STUB).build();

        ClientStatusCommand clientStatusCommand =
                new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_UP_STUB);

        String expectedMessage =
                String.format(ClientStatusCommand.MESSAGE_STATUS_UP_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.commitAddressBook();

        assertCommandSuccess(clientStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ClientStatusCommand clientStatusCommand = new ClientStatusCommand(outOfBoundIndex, CLIENT_STATUS_UP_STUB);

        assertCommandFailure(clientStatusCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        ClientStatusCommand clientStatusCommand = new ClientStatusCommand(outOfBoundIndex, CLIENT_STATUS_UP_STUB);
        assertCommandFailure(clientStatusCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ClientStatusCommand standardCommand =
                new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_UP_STUB);

        // same values -> returns true
        ClientStatusCommand commandWithSameValues = new ClientStatusCommand(INDEX_FIRST_PERSON, CLIENT_STATUS_UP_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

    }
}
