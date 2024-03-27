package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_confirmation() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_CONFIRMATION, expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        model.setAwaitingClear(true);
        model.setConfirmClear(true);
        Model expectedModel = new ModelManager();
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_cancelled() {
        Model model = new ModelManager();
        model.setAwaitingClear(true);
        model.setConfirmClear(false);
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_CANCELLED, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_confirmation() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_CONFIRMATION, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setAwaitingClear(true);
        model.setConfirmClear(true);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_cancelled() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.setAwaitingClear(true);
        model.setConfirmClear(false);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_CANCELLED, expectedModel);
    }
}
