package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalNetConnect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.SupplierBuilder;

/**
 * Contains integration tests (interaction with the Model) for
 * {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNetConnect(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Person validClient = new ClientBuilder().withName("John").build();

        Model expectedModel = new ModelManager(model.getNetConnect(), new UserPrefs());
        expectedModel.addPerson(validClient);

        assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validClient)),
                expectedModel);
    }

    @Test
    public void execute_newEmployee_success() {
        Person validEmployee = new EmployeeBuilder().build();

        Model expectedModel = new ModelManager(model.getNetConnect(), new UserPrefs());
        expectedModel.addPerson(validEmployee);

        assertCommandSuccess(new AddCommand(validEmployee), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validEmployee)),
                expectedModel);
    }

    @Test
    public void execute_newSupplier_success() {
        Person validSupplier = new SupplierBuilder().build();

        Model expectedModel = new ModelManager(model.getNetConnect(), new UserPrefs());
        expectedModel.addPerson(validSupplier);

        assertCommandSuccess(new AddCommand(validSupplier), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validSupplier)),
                expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        model.updateFilteredPersonList(new RoleContainsKeywordsPredicate("client"));
        Person clientInList = model.getFilteredPersonList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateEmployee_throwsCommandException() {
        model.updateFilteredPersonList(new RoleContainsKeywordsPredicate("employee"));
        Person employeeInList = model.getFilteredPersonList().get(0);
        assertCommandFailure(new AddCommand(employeeInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateSupplier_throwsCommandException() {
        model.updateFilteredPersonList(new RoleContainsKeywordsPredicate("supplier"));
        Person supplierInList = model.getFilteredPersonList().get(0);
        assertCommandFailure(new AddCommand(supplierInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }
}
