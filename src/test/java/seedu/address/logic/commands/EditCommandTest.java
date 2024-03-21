package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_ONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonWithStudentId;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudentIds.ID_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudentIds.ID_SECOND_PERSON;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Person firstPerson = null;
        List<Person> filteredList = model.getFilteredPersonList();
        for (Person person : filteredList) {
            if (person.getStudentId().equals(ID_FIRST_PERSON)) {
                firstPerson = person;
                break;
            }
        }

        assertNotNull(firstPerson); // Ensure the person with ID_FIRST_PERSON exists

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB)
                .withSecondParentPhone(VALID_PHONE_ONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_ONE_BOB, "2")
                .withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Person editedPerson = null;
        List<Person> filteredList = model.getFilteredPersonList();
        for (Person person : filteredList) {
            if (person.getStudentId().equals(ID_FIRST_PERSON)) {
                editedPerson = person;
                break;
            }
        }

        assertNotNull(editedPerson); // Ensure the person with ID_FIRST_PERSON exists

        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON, new EditPersonDescriptor());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // Show only the person with the specified student ID in the filtered list
        showPersonWithStudentId(model, ID_FIRST_PERSON);

        // Retrieve the person from the filtered list using the specified student ID
        boolean personFound = false;
        Person personInFilteredList = null;
        List<Person> filteredList = model.getFilteredPersonList();
        for (Person person : filteredList) {
            if (person.getStudentId().equals(ID_FIRST_PERSON)) {
                personInFilteredList = person;
                personFound = true;
                break;
            }
        }
        assertTrue(personFound); // Ensure the person with the specified student ID exists

        // Edit the person's name
        Person editedPerson = new PersonBuilder(personInFilteredList)
                .withName(VALID_NAME_BOB)
                .build();

        // Create the edit command
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        // Define the expected message after executing the edit command
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        // Create the expected model after executing the edit command
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personInFilteredList, editedPerson);

        // Assert the success of the command execution
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        // Retrieve the first person from the unfiltered list using the specified student ID
        Person firstPerson = null;
        List<Person> unfilteredList = model.getAddressBook().getPersonList();
        for (Person person : unfilteredList) {
            if (person.getStudentId().equals(ID_FIRST_PERSON)) {
                firstPerson = person;
                break;
            }
        }
        assertNotNull(firstPerson); // Ensure the person with the specified student ID exists

        // Create the edit command with the student ID of the second person and the descriptor of the first person
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(new StudentId("00002"), descriptor);

        // Assert that executing this command results in failure with the expected error message
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }


    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonWithStudentId(model, ID_FIRST_PERSON);

        // Retrieve the person from the filtered list using the specified student ID
        boolean personFound = false;
        Person personInList = null;
        List<Person> unfilteredList = model.getAddressBook().getPersonList();
        for (Person person : unfilteredList) {
            if (person.getStudentId().equals(ID_SECOND_PERSON)) {
                personInList = person;
                personFound = true;
                break;
            }
        }
        assertTrue(personFound); // Ensure the person with the specified student ID exists

        // Create the edit command with the student ID of the first person and the descriptor of the second person
        EditCommand editCommand = new EditCommand(ID_FIRST_PERSON,
                new EditPersonDescriptorBuilder(personInList).build());
        System.out.println(EditCommand.MESSAGE_DUPLICATE_PERSON);
        System.out.println(editCommand);
        // Assert that executing this command results in failure with the expected error message
        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidStudentIdUnfilteredList_failure() {
        // Create the edit command with an invalid student ID
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(new StudentId("99999"), descriptor);

        // Assert that executing this command results in failure with the expected error message
        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STUDENT_ID);
    }





    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(ID_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(ID_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(ID_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(ID_FIRST_PERSON, DESC_BOB)));
    }

    @Test
    public void getEditedPhone_bothPhonesNull_returnsEmpty() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();

        Optional<Phone> result = descriptor.getEditedPhone();

        assertFalse(result.isPresent());
    }

    @Test
    public void getEditedPhone_firstParentPhoneNotNull_returnsFirstParentPhone() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        Phone expectedPhone = new Phone("12345678");
        descriptor.setFirstParentPhone(expectedPhone);

        Optional<Phone> result = descriptor.getEditedPhone();

        assertTrue(result.isPresent());
        assertEquals(expectedPhone, result.get());
    }

    @Test
    public void getEditedPhone_firstParentPhoneNull_returnsSecondParentPhone() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor();
        Phone expectedPhone = new Phone("87654321");
        descriptor.setSecondParentPhone(expectedPhone);

        Optional<Phone> result = descriptor.getEditedPhone();

        assertTrue(result.isPresent());
        assertEquals(expectedPhone, result.get());
    }

    @Test
    public void toStringMethod() {
        StudentId targetId = new StudentId("00001");

        // Case 1: firstParentPhone is not null
        EditPersonDescriptor descriptorWithFirstParentPhone = new EditPersonDescriptor();
        Phone firstParentPhone = new Phone("12345678");
        descriptorWithFirstParentPhone.setFirstParentPhone(firstParentPhone);
        EditCommand editCommandWithFirstParentPhone = new EditCommand(targetId, descriptorWithFirstParentPhone);

        String expectedWithFirstParentPhone = "seedu.address.logic.commands.EditCommand{studentId="
                + targetId
                + ", editPersonDescriptor="
                + descriptorWithFirstParentPhone
                + "}";

        assertEquals(expectedWithFirstParentPhone, editCommandWithFirstParentPhone.toString());

        // Case 2: firstParentPhone is null, secondParentPhone is not null
        EditPersonDescriptor descriptorWithSecondParentPhone = new EditPersonDescriptor();
        Phone secondParentPhone = new Phone("87654321");
        descriptorWithSecondParentPhone.setSecondParentPhone(secondParentPhone);
        EditCommand editCommandWithSecondParentPhone = new EditCommand(targetId, descriptorWithSecondParentPhone);

        String expectedWithSecondParentPhone = "seedu.address.logic.commands.EditCommand{studentId="
                + targetId
                + ", editPersonDescriptor="
                + descriptorWithSecondParentPhone
                + "}";

        assertEquals(expectedWithSecondParentPhone, editCommandWithSecondParentPhone.toString());
    }

}
