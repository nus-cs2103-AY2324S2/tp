package vitalconnect.logic.commands;

import static vitalconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import org.junit.jupiter.api.Test;

import vitalconnect.model.Clinic;
import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyClinic_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClinic_success() {
        Model model = new ModelManager(getTypicalClinic(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalClinic(), new UserPrefs());
        expectedModel.setClinic(new Clinic());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
