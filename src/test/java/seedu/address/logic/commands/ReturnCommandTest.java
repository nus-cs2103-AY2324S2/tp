package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_JACKER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.JACKER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ReturnCommandTest {
    private static final String BOOKLIST_STUB = "";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_returnUnfilteredList_success() {
        Person editedPerson = new PersonBuilder(JACKER).withBook(BOOKLIST_STUB).withMeritScore(0).build();

        ReturnCommand returnCommand = new ReturnCommand(INDEX_JACKER);

        String expectedMessage = String.format(ReturnCommand.MESSAGE_RETURN_BOOK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(JACKER, editedPerson);

        assertCommandSuccess(returnCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ReturnCommand returnCommand = new ReturnCommand(outOfBoundIndex);

        assertCommandFailure(returnCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidBookListFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        ReturnCommand returnCommand = new ReturnCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(returnCommand, model, Messages.MESSAGE_EMPTY_BOOKLIST_FIELD);
    }

    @Test
    public void equals() {
        ReturnCommand returnFirstCommand = new ReturnCommand(INDEX_FIRST_PERSON);
        ReturnCommand returnSecondCommand = new ReturnCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(returnFirstCommand.equals(returnFirstCommand));

        // same values -> returns true
        ReturnCommand returnFirstCommandCopy = new ReturnCommand(INDEX_FIRST_PERSON);
        assertTrue(returnFirstCommand.equals(returnFirstCommandCopy));

        // different types -> returns false
        assertFalse(returnFirstCommand.equals(1));

        // null -> returns false
        assertFalse(returnFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(returnFirstCommand.equals(returnSecondCommand));
    }
}
