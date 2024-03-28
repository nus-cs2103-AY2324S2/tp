package seedu.hirehub.logic.commands;

import static seedu.hirehub.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.hirehub.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.hirehub.logic.Messages;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.ModelManager;
import seedu.hirehub.model.UserPrefs;
import seedu.hirehub.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setLastMentionedPerson(personToDelete);
        DeleteCommand deleteCommand = new DeleteCommand();

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setLastMentionedPerson(personToDelete);
        DeleteCommand deleteCommand = new DeleteCommand();

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        Assertions.assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
