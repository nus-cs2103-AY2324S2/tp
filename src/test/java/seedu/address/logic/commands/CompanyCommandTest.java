package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Company;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class CompanyCommandTest {

    private static final String COMPANY_STUB = "Some company";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addCompanyUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withCompany(COMPANY_STUB).build();

        CompanyCommand companyCommand = new CompanyCommand(editedPerson.getName().fullName,
                new Company(editedPerson.getCompany().value));

        String expectedMessage = String.format(CompanyCommand.MESSAGE_ADD_COMPANY_SUCCESS,
                editedPerson.getName().fullName, editedPerson.getCompany().value);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(companyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteCompanyUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withCompany("").build();

        CompanyCommand companyCommand = new CompanyCommand(editedPerson.getName().fullName,
                new Company(editedPerson.getCompany().toString()));

        String expectedMessage = String.format(CompanyCommand.MESSAGE_DELETE_COMPANY_SUCCESS,
                editedPerson.getName().fullName);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(companyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withCompany(COMPANY_STUB).build();

        CompanyCommand companyCommand = new CompanyCommand(editedPerson.getName().fullName,
                new Company(editedPerson.getCompany().value));

        String expectedMessage = String.format(CompanyCommand.MESSAGE_ADD_COMPANY_SUCCESS,
                editedPerson.getName().fullName, editedPerson.getCompany().value);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(companyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonName_throwsCommandException() {
        CompanyCommand companyCommand = new CompanyCommand("",
                new Company(VALID_COMPANY_AMY));
        assertCommandFailure(companyCommand, model, CompanyCommand.MESSAGE_EMPTY_NAME);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        CompanyCommand companyCommand = new CompanyCommand("ABC",
                new Company(VALID_COMPANY_AMY));
        assertCommandFailure(companyCommand, model, String.format(CompanyCommand.MESSAGE_PERSON_NOT_FOUND, "ABC"));
    }

    @Test
    public void equals() {
        final CompanyCommand standardCommand = new CompanyCommand("Amy Reale",
                new Company(VALID_COMPANY_AMY));
        // same values -> returns true
        CompanyCommand commandWithSameValues = new CompanyCommand("Amy Reale",
                new Company(VALID_COMPANY_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different company tag -> returns false
        assertFalse(standardCommand.equals(new CompanyCommand("Bob Tan",
                new Company(VALID_COMPANY_BOB))));
    }
}
