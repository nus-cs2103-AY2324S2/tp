package seedu.address.logic.commands;



import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_undoAfterAddingPersons_success() {
        UndoCommand command = new UndoCommand();
        String expectedMessage = command.MESSAGE_SUCCESS;
        model.addPerson(AMY);
        model.commitAddressBook();
        expectedModel.addPerson(AMY);
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_undoWithoutCommandsToUndo_failure() {
        UndoCommand command = new UndoCommand();
        String expectedMessage = command.MESSAGE_FAILURE;
        assertCommandFailure(command, model, expectedMessage);
    }
    @Test
    public void execute_undoAfterExecutingCommandsThatDoNotAffectAddressBook_failure() {
        UndoCommand command = new UndoCommand();
        String expectedMessage = command.MESSAGE_FAILURE;
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertCommandFailure(command, model, expectedMessage);
    }

}
