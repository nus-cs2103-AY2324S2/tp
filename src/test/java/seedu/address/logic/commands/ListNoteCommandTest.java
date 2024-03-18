package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showNotesAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListNoteCommand.
 */
class ListNoteCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListNoteCommand(), model, ListNoteCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validIndex_filteredListShowsPersonNotes() {
        showNotesAtIndex(expectedModel, INDEX_FIRST_PERSON);
        ListNoteCommand listNoteCommand = new ListNoteCommand(INDEX_FIRST_PERSON);

        Person person = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String commandMsg = String.format(Messages.MESSAGE_LIST_NOTE_SUCCESS, person.getName());

        assertCommandSuccess(listNoteCommand, model, commandMsg, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ListNoteCommand listNoteCommand = new ListNoteCommand(outOfBoundIndex);

        assertCommandFailure(listNoteCommand, model, Messages.MESSAGE_INVALID_INDEX);
    }

}
