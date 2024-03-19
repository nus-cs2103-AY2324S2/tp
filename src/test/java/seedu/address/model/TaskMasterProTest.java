package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.getTypicalTaskMasterPro;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.exceptions.DuplicateEmployeeException;
import seedu.address.model.task.Task;
import seedu.address.testutil.EmployeeBuilder;

public class TaskMasterProTest {

    private final TaskMasterPro taskMasterPro = new TaskMasterPro();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskMasterPro.getEmployeeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskMasterPro.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskMasterPro_replacesData() {
        TaskMasterPro newData = getTypicalTaskMasterPro();
        taskMasterPro.resetData(newData);
        assertEquals(newData, taskMasterPro);
    }

    @Test
    public void resetData_withDuplicateEmployees_throwsDuplicateEmployeeException() {
        // Two employees with the same identity fields
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Employee> newEmployees = Arrays.asList(ALICE, editedAlice);
        TaskMasterProStub newData = new TaskMasterProStub(newEmployees);

        assertThrows(DuplicateEmployeeException.class, () -> taskMasterPro.resetData(newData));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskMasterPro.hasEmployee(null));
    }

    @Test
    public void hasEmployee_employeeNotInTaskMasterPro_returnsFalse() {
        assertFalse(taskMasterPro.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeInTaskMasterPro_returnsTrue() {
        taskMasterPro.addEmployee(ALICE);
        assertTrue(taskMasterPro.hasEmployee(ALICE));
    }

    @Test
    public void hasEmployee_employeeWithSameIdentityFieldsInTaskMasterPro_returnsTrue() {
        taskMasterPro.addEmployee(ALICE);
        Employee editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(taskMasterPro.hasEmployee(editedAlice));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskMasterPro.getEmployeeList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = TaskMasterPro.class.getCanonicalName()
                + "{Employees=" + taskMasterPro.getEmployeeList() + "}";
        assertEquals(expected, taskMasterPro.toString());
    }

    /**
     * A stub ReadOnlyTaskMasterPro whose employees list can violate interface constraints.
     */
    private static class TaskMasterProStub implements ReadOnlyTaskMasterPro {
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();

        TaskMasterProStub(Collection<Employee> employees) {
            this.employees.setAll(employees);
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return employees;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return null;
        }
    }
}
