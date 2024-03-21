package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtId;
import static seedu.address.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalNetConnect;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NetConnect;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Client;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.SupplierBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {

    private final Model model = new ModelManager(getTypicalNetConnect(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new ClientBuilder().withId(1).withName("New Name").build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetConnect(model.getNetConnect()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Id idLastPerson = Id.generateId(model.getFilteredPersonList().size());
        Person lastPerson = model.getPersonById(idLastPerson);

        EmployeeBuilder personInList = new EmployeeBuilder((Employee) lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(idLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetConnect(model.getNetConnect()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = model.getPersonById(ID_FIRST_PERSON);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new NetConnect(model.getNetConnect()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredListForClient_success() {
        showPersonAtId(model, ID_FIRST_PERSON);

        Person personInFilteredList = model.getPersonById(ID_FIRST_PERSON);
        Person editedClient = new ClientBuilder((Client) personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON,
                new EditPersonDescriptorBuilder(editedClient).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedClient));

        Model expectedModel = new ModelManager(new NetConnect(model.getNetConnect()), new UserPrefs());
        expectedModel.setPerson(model.getPersonById(ID_FIRST_PERSON), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getPersonById(ID_FIRST_PERSON);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(ID_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtId(model, ID_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = model.getPersonById(ID_SECOND_PERSON);
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIdUnfilteredList_failure() {
        Id outOfBoundId = Id.generateNextId();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundId, descriptor);

        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_INVALID_PERSON_ID, outOfBoundId.value));
    }

    @Test
    public void execute_invalidPersonIdFilteredList_failure() {
        showPersonAtId(model, ID_FIRST_PERSON);

        Id outOfBoundId = Id.generateNextId();
        EditCommand editCommand = new EditCommand(outOfBoundId,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model,
                String.format(Messages.MESSAGE_INVALID_PERSON_ID, outOfBoundId.value));
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(ID_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(ID_FIRST_PERSON, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different id -> returns false
        assertNotEquals(standardCommand, new EditCommand(ID_SECOND_PERSON, DESC_AMY));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(ID_FIRST_PERSON, DESC_BOB));
    }

    @Test
    public void toStringMethod() {
        Id id = Id.generateId(1);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(id, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{id=" + id + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

    @Test
    public void createEditedPerson_editClient_success() throws CommandException {
        Person personToEdit = new ClientBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName("Updated Name")
                .withPhone("98765432")
                .withEmail("updated@example.com")
                .withAddress("Updated Address")
                .withRemark("Updated Remark")
                .withTags("tag1", "tag2")
                .withPreferences("Updated Preferences")
                .withProducts("Product A", "Product B")
                .build();

        Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);

        assertEquals("Updated Name", editedPerson.getName().toString());
        assertEquals("98765432", editedPerson.getPhone().toString());
        assertEquals("updated@example.com", editedPerson.getEmail().toString());
        assertEquals("Updated Address", editedPerson.getAddress().toString());
        assertEquals("Updated Remark", editedPerson.getRemark().toString());
        assertEquals(new HashSet<>(Arrays.asList(new Tag("tag1"), new Tag("tag2"))), editedPerson.getTags());
        assertTrue(editedPerson instanceof Client);
        Client editedClient = (Client) editedPerson;
        assertEquals("Updated Preferences", editedClient.getPreferences());
        assertEquals(Arrays.asList("Product A", "Product B"), editedClient.getProducts().getProducts());
    }

    @Test
    public void createEditedPerson_editEmployee_success() throws CommandException {
        Person personToEdit = new EmployeeBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName("Updated Name")
                .withPhone("98765432")
                .withEmail("updated@example.com")
                .withAddress("Updated Address")
                .withRemark("Updated Remark")
                .withTags("tag1", "tag2")
                .withDepartment("Updated Department")
                .withJobTitle("Updated Job Title")
                .withSkills("Skill A", "Skill B")
                .build();

        Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);

        assertEquals("Updated Name", editedPerson.getName().toString());
        assertEquals("98765432", editedPerson.getPhone().toString());
        assertEquals("updated@example.com", editedPerson.getEmail().toString());
        assertEquals("Updated Address", editedPerson.getAddress().toString());
        assertEquals("Updated Remark", editedPerson.getRemark().toString());
        assertEquals(new HashSet<>(Arrays.asList(new Tag("tag1"), new Tag("tag2"))), editedPerson.getTags());
        assertTrue(editedPerson instanceof Employee);
        Employee editedEmployee = (Employee) editedPerson;
        assertEquals("Updated Department", editedEmployee.getDepartment().toString());
        assertEquals("Updated Job Title", editedEmployee.getJobTitle().toString());
        HashSet<String> skillsSet = new HashSet<>();
        skillsSet.add("Skill A");
        skillsSet.add("Skill B");
        assertEquals(skillsSet, editedEmployee.getSkills().getSkills());
    }

    @Test
    public void createEditedPerson_editSupplier_success() throws CommandException {
        Person personToEdit = new SupplierBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withName("Updated Name")
                .withPhone("98765432")
                .withEmail("updated@example.com")
                .withAddress("Updated Address")
                .withRemark("Updated Remark")
                .withTags("tag1", "tag2")
                .withTermsOfService("Updated Terms of Service")
                .withProducts("Product A", "Product B")
                .build();

        Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);

        assertEquals("Updated Name", editedPerson.getName().toString());
        assertEquals("98765432", editedPerson.getPhone().toString());
        assertEquals("updated@example.com", editedPerson.getEmail().toString());
        assertEquals("Updated Address", editedPerson.getAddress().toString());
        assertEquals("Updated Remark", editedPerson.getRemark().toString());
        assertEquals(new HashSet<>(Arrays.asList(new Tag("tag1"), new Tag("tag2"))), editedPerson.getTags());
        assertTrue(editedPerson instanceof Supplier);
        Supplier editedSupplier = (Supplier) editedPerson;
        assertEquals("Updated Terms of Service", editedSupplier.getTermsOfService().toString());
        assertEquals(Arrays.asList("Product A", "Product B"), editedSupplier.getProducts().getProducts());
    }

    @Test
    public void createEditedPerson_noChanges_success() throws CommandException {
        Person personToEdit = new ClientBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        Person editedPerson = EditCommand.createEditedPerson(personToEdit, editPersonDescriptor);

        assertEquals(personToEdit, editedPerson);
    }

    @Test
    public void createEditedPerson_invalidClientProperty_throwsCommandException() {
        Person personToEdit = new ClientBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withDepartment("Updated Department")
                .build();

        assertThrows(CommandException.class, () -> EditCommand.createEditedPerson(personToEdit, editPersonDescriptor));
    }

    @Test
    public void createEditedPerson_invalidEmployeeProperty_throwsCommandException() {
        Person personToEdit = new EmployeeBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withPreferences("Updated Preferences")
                .build();

        assertThrows(CommandException.class, () -> EditCommand.createEditedPerson(personToEdit, editPersonDescriptor));
    }

    @Test
    public void createEditedPerson_invalidSupplierProperty_throwsCommandException() {
        Person personToEdit = new SupplierBuilder().build();
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withSkills("Skill A", "Skill B")
                .build();

        assertThrows(CommandException.class, () -> EditCommand.createEditedPerson(personToEdit, editPersonDescriptor));
    }
}
