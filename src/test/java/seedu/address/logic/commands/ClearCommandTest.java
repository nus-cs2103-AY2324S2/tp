package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyContactList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, false);
    }

    @Test
    public void execute_nonEmptyContactList_success() {
        Model model = new ModelManager(getTypicalContactList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalContactList(), new UserPrefs());
        expectedModel.setContactList(new ContactList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, false);
    }

}
