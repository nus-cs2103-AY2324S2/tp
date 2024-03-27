package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.address.testutil.TypicalEmployees.getTypicalTaskMasterPro;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EMPLOYEE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskMasterPro;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EditEmployeeDescriptorBuilder;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalTaskMasterPro(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Employee editedEmployee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(editedEmployee).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EMPLOYEE, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                Messages.format(editedEmployee));

        Model expectedModel = new ModelManager(new TaskMasterPro(model.getTaskMasterPro()), new UserPrefs());
        expectedModel.setEmployee(model.getFilteredEmployeeList().get(0), editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEmployee = Index.fromOneBased(model.getFilteredEmployeeList().size());
        Employee lastEmployee = model.getFilteredEmployeeList().get(indexLastEmployee.getZeroBased());

        EmployeeBuilder employeeInList = new EmployeeBuilder(lastEmployee);
        Employee editedEmployee = employeeInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastEmployee, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                Messages.format(editedEmployee));

        Model expectedModel = new ModelManager(new TaskMasterPro(model.getTaskMasterPro()), new UserPrefs());
        expectedModel.setEmployee(lastEmployee, editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EMPLOYEE, new EditEmployeeDescriptor());
        Employee editedEmployee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                Messages.format(editedEmployee));

        Model expectedModel = new ModelManager(new TaskMasterPro(model.getTaskMasterPro()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        Employee employeeInFilteredList = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        Employee editedEmployee = new EmployeeBuilder(employeeInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EMPLOYEE,
                new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                Messages.format(editedEmployee));

        Model expectedModel = new ModelManager(new TaskMasterPro(model.getTaskMasterPro()), new UserPrefs());
        expectedModel.setEmployee(model.getFilteredEmployeeList().get(0), editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEmployeeUnfilteredList_failure() {
        Employee firstEmployee = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(firstEmployee).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_EMPLOYEE, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

    @Test
    public void execute_duplicateEmployeeFilteredList_failure() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        // edit employee in filtered list into a duplicate in address book
        Employee employeeInList = model.getTaskMasterPro().getEmployeeList().get(INDEX_SECOND_EMPLOYEE.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EMPLOYEE,
                new EditEmployeeDescriptorBuilder(employeeInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

    @Test
    public void execute_invalidEmployeeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEmployeeList().size() + 1);
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EMPLOYEEID);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidEmployeeIndexFilteredList_failure() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);
        Index outOfBoundIndex = INDEX_SECOND_EMPLOYEE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskMasterPro().getEmployeeList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EMPLOYEEID);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_EMPLOYEE, DESC_AMY);

        // same values -> returns true
        EditEmployeeDescriptor copyDescriptor = new EditCommand.EditEmployeeDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_EMPLOYEE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_EMPLOYEE, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_EMPLOYEE, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditEmployeeDescriptor editEmployeeDescriptor = new EditEmployeeDescriptor();
        EditCommand editCommand = new EditCommand(index, editEmployeeDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editEmployeeDescriptor="
                + editEmployeeDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
