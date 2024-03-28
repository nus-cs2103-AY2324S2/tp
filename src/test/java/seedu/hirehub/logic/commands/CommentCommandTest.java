package seedu.hirehub.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hirehub.logic.commands.CommandTestUtil.VALID_COMMENT_AMY;
import static seedu.hirehub.logic.commands.CommandTestUtil.VALID_COMMENT_BOB;
import static seedu.hirehub.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hirehub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hirehub.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.hirehub.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.hirehub.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.hirehub.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.Messages;
import seedu.hirehub.model.AddressBook;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.ModelManager;
import seedu.hirehub.model.UserPrefs;
import seedu.hirehub.model.person.Comment;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CommentCommand.
 */
public class CommentCommandTest {

    private static final String COMMENT_STUB = "Some comment";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addCommentUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withComment(COMMENT_STUB).build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(editedPerson.getComment().value));

        String expectedMessage = String.format(CommentCommand.MESSAGE_ADD_COMMENT_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteCommentUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withComment("").build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(editedPerson.getComment().value));

        String expectedMessage = String.format(CommentCommand.MESSAGE_DELETE_COMMENT_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased())).withComment(COMMENT_STUB).build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(editedPerson.getComment().value));
        String expectedMessage = String.format(CommentCommand.MESSAGE_ADD_COMMENT_SUCCESS,
                Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CommentCommand commentCommand = new CommentCommand(outOfBoundIndex, new Comment(VALID_COMMENT_BOB));

        assertCommandFailure(commentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        CommentCommand commentCommand = new CommentCommand(outOfBoundIndex, new Comment(VALID_COMMENT_BOB));

        assertCommandFailure(commentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final CommentCommand standardCommand = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(VALID_COMMENT_AMY));
        // same values -> returns true
        CommentCommand commandWithSameValues = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(VALID_COMMENT_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new CommentCommand(INDEX_SECOND_PERSON,
                new Comment(VALID_COMMENT_AMY))));
        // different comment -> returns false
        assertFalse(standardCommand.equals(new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(VALID_COMMENT_BOB))));
    }
}
