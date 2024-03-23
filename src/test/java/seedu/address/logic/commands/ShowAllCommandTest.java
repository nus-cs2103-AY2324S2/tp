package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalEmployees.getTypicalEmployees;

import org.junit.jupiter.api.Test;

import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithFilteredEmployeeList;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ShowAllCommand.
 */
public class ShowAllCommandTest {

    @Test
    public void execute_showAll_success() {
        ModelStub modelStub = new ModelStubWithFilteredEmployeeList();
        ShowAllCommand showAllCommand = new ShowAllCommand();

        assertDoesNotThrow(() -> showAllCommand.execute(modelStub));
        assertEquals(getTypicalEmployees(), modelStub.getFilteredEmployeeList());
    }

    // Model stubs and other necessary test utilities
}
