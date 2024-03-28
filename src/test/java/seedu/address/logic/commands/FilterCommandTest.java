package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithEmployee;
import seedu.address.model.ModelStubWithNoEmployee;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandTest {

    @Test
    public void execute_validFilter_success() {
        Employee testEmployee = new EmployeeBuilder().withName("Alice").build();

        ModelStub modelStub = new ModelStubWithEmployee(Collections.singletonList(testEmployee));

        Predicate<Employee> predicate = employee -> employee.getName().equals(testEmployee.getName());
        FilterCommand filterCommand = new FilterCommand(predicate, "Name: Alice");

        assertDoesNotThrow(() -> filterCommand.execute(modelStub));
        assertEquals(Arrays.asList(testEmployee), modelStub.getFilteredEmployeeList());
    }

    @Test
    public void execute_invalidFilter_throwsCommandException() {
        ModelStub modelStub = new ModelStubWithNoEmployee();
        Predicate<Employee> predicate = employee -> false;
        FilterCommand filterCommand = new FilterCommand(predicate, "Name: Nonexistent");

        assertThrows(CommandException.class, () -> filterCommand.execute(modelStub));
    }

}
