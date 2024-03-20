package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortCompanyList() {
        SortCommand command = new SortCommand();
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        for (int i = 1; i < model.getFilteredCompanyList().size(); i++) {
            assert model.getFilteredCompanyList().get(i).getName().compareToIgnoreCase(
                    model.getFilteredCompanyList().get(i - 1).getName()) >= 0;
        }
    }
}

