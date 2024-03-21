package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_redoAfterSuccessfulUndo_success() {
        RedoCommand command = new RedoCommand();
        String expectedMessage = command.MESSAGE_SUCCESS;
        model.addPerson(AMY);
        model.commitAddressBook();
        model.undoAddressBook();
        expectedModel.addPerson(AMY);
        expectedModel.commitAddressBook();
        expectedModel.undoAddressBook();
        expectedModel.redoAddressBook();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_redoWithoutPreviousUndo_failure() {
        RedoCommand command = new RedoCommand();
        String expectedMessage = command.MESSAGE_FAILURE;
        assertCommandFailure(command, model, expectedMessage);
    }


}
