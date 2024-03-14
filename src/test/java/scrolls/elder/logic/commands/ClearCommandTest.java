package scrolls.elder.logic.commands;

import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import scrolls.elder.model.AddressBook;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.testutil.TypicalPersons;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
