package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.UpdatePersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateCommand.
 */
public class UpdateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person updatedPerson = new PersonBuilder().build();
        UpdateCommand.UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder(updatedPerson).build();
        UpdateCommand updateCommand = new UpdateCommand(ALICE.getName(), descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PERSON_SUCCESS, Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Name lastPersonName = lastPerson.getName();

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person updatedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        UpdateCommand.UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        UpdateCommand updateCommand = new UpdateCommand(lastPersonName, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PERSON_SUCCESS, Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, updatedPerson);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateCommand updateCommand = new UpdateCommand(ALICE.getName(), new UpdateCommand.UpdatePersonDescriptor());
        Person updatedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PERSON_SUCCESS, Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtName(model, ALICE.getName());

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        UpdateCommand updateCommand = new UpdateCommand(ALICE.getName(),
                new UpdatePersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PERSON_SUCCESS, Messages.format(updatedPerson));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), updatedPerson);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

//    @Test
//    public void execute_duplicatePersonUnfilteredList_failure() {
//        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder(firstPerson).build();
//        UpdateCommand updateCommand = new UpdateCommand(INDEX_SECOND_PERSON, descriptor);
//
//        assertCommandFailure(updateCommand, model, UpdateCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        // update person in filtered list into a duplicate in address book
//        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
//        UpdateCommand updateCommand = new UpdateCommand(INDEX_FIRST_PERSON,
//                new UpdatePersonDescriptorBuilder(personInList).build());
//
//        assertCommandFailure(updateCommand, model, UpdateCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
//        UpdatePersonDescriptor descriptor = new UpdatePersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
//        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Update filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidPersonIndexFilteredList_failure() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
//
//        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex,
//                new UpdatePersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final UpdateCommand standardCommand = new UpdateCommand(INDEX_FIRST_PERSON, DESC_AMY);
//
//        // same values -> returns true
//        UpdatePersonDescriptor copyDescriptor = new UpdatePersonDescriptor(DESC_AMY);
//        UpdateCommand commandWithSameValues = new UpdateCommand(INDEX_FIRST_PERSON, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_SECOND_PERSON, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_FIRST_PERSON, DESC_BOB)));
//    }

    @Test
    public void toStringMethod() {
        Name name = ALICE.getName();
        UpdateCommand.UpdatePersonDescriptor updatePersonDescriptor = new UpdateCommand.UpdatePersonDescriptor();
        UpdateCommand updateCommand = new UpdateCommand(name, updatePersonDescriptor);
        String expected = UpdateCommand.class.getCanonicalName() + "{name=" + name + ", updatePersonDescriptor="
                + updatePersonDescriptor + "}";
        assertEquals(expected, updateCommand.toString());
    }

}
