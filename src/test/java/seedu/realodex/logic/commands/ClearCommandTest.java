package seedu.realodex.logic.commands;

import static seedu.realodex.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.Model;
import seedu.realodex.model.ModelManager;
import seedu.realodex.model.Realodex;
import seedu.realodex.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyRealodex_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRealodex_success() {
        Model model = new ModelManager(getTypicalRealodex(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalRealodex(), new UserPrefs());
        expectedModel.setRealodex(new Realodex());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
