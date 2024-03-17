package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewAppointmentsCommand.
 */
public class ViewAppointmentsCommandTest {


    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    // Basic test for now, will include more as we develop.
    @Test
    public void execute_viewAppointments_success() {
        String expectedMessage = "Appointments:\n"
                + "23:59 SUN";
        assertCommandSuccess(new ViewAppointmentsCommand(), model, expectedMessage, expectedModel);
    }

}
