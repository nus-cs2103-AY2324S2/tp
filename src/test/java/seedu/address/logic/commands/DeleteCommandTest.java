package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EMPLOYEE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EMPLOYEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelStubWithEmployee;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Team;
import seedu.address.model.employee.UniqueId;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Employee employeeToDelete = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EMPLOYEE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS,
                Messages.format(employeeToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEmployeeList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validNameUnfilteredList_success() {
        // Prepare the model with a specific employee name
        Employee employee = model.getFilteredEmployeeList().get(0);
        String targetName = employee.getName().fullName;
        DeleteCommand deleteCommand = new DeleteCommand(targetName);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS,
                Messages.format(employee));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEmployee(employee);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        String invalidName = "Invalid Name";
        DeleteCommand deleteCommand = new DeleteCommand(invalidName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_EMPLOYEE_NOT_FOUND);
    }

    @Test
    public void execute_validUidUnfilteredList_success() {
        // Prepare the model with a specific employee UID
        Employee employee = model.getFilteredEmployeeList().get(0);
        UniqueId uid = employee.getUid();
        DeleteCommand deleteCommand = new DeleteCommand(uid);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS,
                Messages.format(employee));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEmployee(employee);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        Employee employeeToDelete = model.getFilteredEmployeeList().get(INDEX_FIRST_EMPLOYEE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EMPLOYEE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS,
                Messages.format(employeeToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);
        showNoEmployee(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showEmployeeAtIndex(model, INDEX_FIRST_EMPLOYEE);

        Index outOfBoundIndex = INDEX_SECOND_EMPLOYEE;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getEmployeeList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidUid_throwsParseException() {
        UniqueId invalidUid = new UniqueId("-1");
        DeleteCommand deleteCommand = new DeleteCommand(invalidUid);

        assertThrows(CommandException.class, () -> deleteCommand.execute(model));
    }

    @Test
    void execute_noTargetIndexNameOrUid_throwsCommandException() {
        // Arrange
        ModelStubWithEmployee model = new ModelStubWithEmployee(new ArrayList<>());
        DeleteCommand deleteCommand = new DeleteCommand();

        assertThrows(CommandException.class, () -> deleteCommand.execute(model));
    }

    @Test
    void deleteByName_multipleEmployeesWithSameName_throwsCommandException() {
        // Arrange
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("johndoe@example.com");
        Address address = new Address("123 Main Street");
        Team team = new Team("A");
        Role role = new Role("Developer");
        Set<Tag> tags = new HashSet<>();
        UniqueId uid1 = new UniqueId("1");
        UniqueId uid2 = new UniqueId("2");

        Employee employee1 = new Employee(name, phone, email, address, team, role, tags, uid1);
        Employee employee2 = new Employee(name, phone, email, address, team, role, tags, uid2);

        ModelStubWithEmployee model = new ModelStubWithEmployee(Arrays.asList(employee1, employee2));

        DeleteCommand deleteCommand = new DeleteCommand(name.fullName);

        // Act and Assert
        assertThrows(CommandException.class, () -> deleteCommand.deleteByName(model));
    }

    @Test
    public void equals_differentTypesOfDeletionCommands() {
        DeleteCommand deleteByIndexCommand = new DeleteCommand(INDEX_FIRST_EMPLOYEE);
        DeleteCommand deleteByNameCommand = new DeleteCommand("John Doe");
        DeleteCommand deleteByUidCommand = new DeleteCommand(new UniqueId("1"));

        // Different types of DeleteCommand should not be equal
        assertFalse(deleteByIndexCommand.equals(deleteByNameCommand));
        assertFalse(deleteByIndexCommand.equals(deleteByUidCommand));
        assertFalse(deleteByNameCommand.equals(deleteByUidCommand));
    }

    @Test
    public void execute_emptyName_throwsCommandException() {
        String emptyName = "";
        DeleteCommand deleteCommand = new DeleteCommand(emptyName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void equals_nullNameAndUid() {
        DeleteCommand deleteByNameCommand = new DeleteCommand((String) null);
        DeleteCommand deleteByUidCommand = new DeleteCommand((UniqueId) null);

        // A DeleteCommand with null name or UID should not be equal to any other DeleteCommand
        assertFalse(deleteByNameCommand.equals(new DeleteCommand("John Doe")));
        assertFalse(deleteByUidCommand.equals(new DeleteCommand(new UniqueId("1"))));

        // A DeleteCommand with null name or UID should be equal to itself
        assertTrue(deleteByNameCommand.equals(deleteByNameCommand));
        assertTrue(deleteByUidCommand.equals(deleteByUidCommand));
    }

    @Test
    public void equals_sameNameDifferentCase() {
        DeleteCommand deleteCommandLowercase = new DeleteCommand("john doe");
        DeleteCommand deleteCommandUppercase = new DeleteCommand("JOHN DOE");

        // Commands with the same name in different cases should be considered equal
        assertTrue(deleteCommandLowercase.equals(deleteCommandUppercase));
    }

    // Ensure that the DeleteCommand with a targetName correctly identifies an empty string as an invalid targetName
    @Test
    public void execute_nullOrEmptyName_throwsCommandException() {
        DeleteCommand deleteCommandWithNullName = new DeleteCommand((String) null);
        DeleteCommand deleteCommandWithEmptyName = new DeleteCommand("");

        assertThrows(CommandException.class, () -> deleteCommandWithNullName.execute(model));
        assertThrows(CommandException.class, () -> deleteCommandWithEmptyName.execute(model));
    }


    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_EMPLOYEE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_EMPLOYEE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_EMPLOYEE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void execute_invalidUidUnfilteredList_throwsParseException() {
        String invalidUid = "uid/non_existent";
        DeleteCommandParser parser = new DeleteCommandParser();

        assertThrows(ParseException.class, () -> parser.parse(invalidUid));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEmployee(Model model) {
        model.updateFilteredEmployeeList(p -> false);

        assertTrue(model.getFilteredEmployeeList().isEmpty());
    }
}
