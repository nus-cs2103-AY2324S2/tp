package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
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
import seedu.address.model.tag.TagStatus;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Person updatedPerson = new PersonBuilder(personInFilteredList).withTag(VALID_TAG_FRIENDS,
                TagStatus.COMPLETE_GOOD).build();
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS,
                TagStatus.COMPLETE_GOOD);

        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_PERSON_SUCCESS, Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, VALID_TAG_FRIENDS,
                TagStatus.COMPLETE_GOOD);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        MarkCommand markCommand = new MarkCommand(outOfBoundIndex, VALID_TAG_FRIENDS,
                TagStatus.COMPLETE_GOOD);

        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MarkCommand standardCommand = new MarkCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS,
                TagStatus.DEFAULT_STATUS);

        // same values -> returns true
        MarkCommand commandWithSameValues = new MarkCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS,
                TagStatus.DEFAULT_STATUS);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_SECOND_PERSON, VALID_TAG_FRIENDS,
                TagStatus.DEFAULT_STATUS)));

        // different tagName -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_FIRST_PERSON, VALID_TAG_HUSBAND,
                TagStatus.DEFAULT_STATUS)));

        // different tagStatus -> returns false
        assertFalse(standardCommand.equals(new MarkCommand(INDEX_FIRST_PERSON, VALID_TAG_FRIENDS,
                TagStatus.COMPLETE_GOOD)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        MarkCommand markCommand = new MarkCommand(index, VALID_TAG_FRIENDS, TagStatus.COMPLETE_GOOD);
        String expected = MarkCommand.class.getCanonicalName() + "{index=" + index + ", tagName="
                + VALID_TAG_FRIENDS + ", tagStatus=" + TagStatus.COMPLETE_GOOD + "}";
        assertEquals(expected, markCommand.toString());
    }

}
