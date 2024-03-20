package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Priority;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code PriorityCommand}.
 */
public class PriorityCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addPriorityUnfilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPriority("high").build();

        PriorityCommand priorityCommand = new PriorityCommand(editedPerson.getName().fullName,
                new Priority(editedPerson.getPriority().value));

        String expectedMessage = String.format(PriorityCommand.MESSAGE_ADD_PRIORITY_SUCCESS,
                "high", "**", editedPerson.getName().fullName, editedPerson.getPhone(), editedPerson.getEmail());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(priorityCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deletePriorityUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPriority("").build();

        PriorityCommand priorityCommand = new PriorityCommand(firstPerson.getName().fullName,
                new Priority(""));
        String expectedMessage = String.format(PriorityCommand.MESSAGE_DELETE_PRIORITY_SUCCESS,
                editedPerson.getName().fullName);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        assertCommandSuccess(priorityCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidPersonName_throwsCommandException() {
        PriorityCommand priorityCommand = new PriorityCommand("",
                new Priority(VALID_PRIORITY_AMY));
        assertCommandFailure(priorityCommand, model, PriorityCommand.MESSAGE_EMPTY_NAME);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        PriorityCommand priorityCommand = new PriorityCommand("ABC",
                new Priority("high"));
        assertCommandFailure(priorityCommand, model, String.format(PriorityCommand.MESSAGE_PERSON_NOT_FOUND, "ABC"));
    }

    @Test
    public void equals() {
        final PriorityCommand standardCommand = new PriorityCommand("Amy Reale",
                new Priority(VALID_PRIORITY_AMY));
        // same values -> returns true
        PriorityCommand commandWithSameValues = new PriorityCommand("Amy Reale",
                new Priority(VALID_PRIORITY_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new PriorityCommand("Bob Tan",
                new Priority(VALID_PRIORITY_BOB))));
    }
}
