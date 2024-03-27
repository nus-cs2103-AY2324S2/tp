package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.logic.Messages;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.Role;
import scrolls.elder.testutil.EditPersonDescriptorBuilder;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalDatastore;
import scrolls.elder.testutil.TypicalIndexes;
import scrolls.elder.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model;
    private PersonStore personStore;
    private Model expectedModel;
    private PersonStore expectedPersonStore;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalDatastore.getTypicalDatastore(), new UserPrefs());
        personStore = model.getMutableDatastore().getMutablePersonStore();
        expectedModel = new ModelManager(new Datastore(model.getDatastore()), new UserPrefs());
        expectedPersonStore = expectedModel.getMutableDatastore().getMutablePersonStore();
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredVolunteerList_success() {
        Person editedPerson = new PersonBuilder(TypicalPersons.BENSON).build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        expectedPersonStore.setPerson(expectedPersonStore.getFilteredVolunteerList().get(1), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredBefriendeeList_success() {
        Person editedPerson = new PersonBuilder(TypicalPersons.ELLE).build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        expectedPersonStore.setPerson(expectedPersonStore.getFilteredVolunteerList().get(0), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {

        Index indexSecondPerson = Index.fromOneBased(2);
        Person secondPerson = personStore.getFilteredVolunteerList().get(indexSecondPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(secondPerson);
        Person editedPerson =
            personInList.withName(CommandTestUtil.VALID_NAME_BOB).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER).build();

        EditCommand.EditPersonDescriptor descriptor =
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER).build();
        EditCommand editCommand = new EditCommand(indexSecondPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        expectedPersonStore.setPerson(
            expectedPersonStore.getFilteredVolunteerList().get(indexSecondPerson.getZeroBased()), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredVolunteerList_success() {
        EditCommand.EditPersonDescriptor epd = new EditCommand.EditPersonDescriptor();
        epd.setRole(new Role(CommandTestUtil.VALID_ROLE_VOLUNTEER));
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, epd);
        Person editedPerson = model.getDatastore().getPersonStore().getFilteredVolunteerList()
            .get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredVolunteerList_success() {
        CommandTestUtil.showVolunteerAtIndex(personStore, TypicalIndexes.INDEX_SECOND_PERSON);
        Person personInFilteredVolunteerList =
            personStore.getFilteredVolunteerList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        Person editedPerson = new PersonBuilder(personInFilteredVolunteerList)
            .withName(CommandTestUtil.VALID_NAME_BOB)
            .build();

        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        expectedPersonStore.setPerson(expectedPersonStore.getFilteredVolunteerList().get(1), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredBefriendeeList_success() {
        CommandTestUtil.showBefriendeeAtIndex(personStore, TypicalIndexes.INDEX_SECOND_PERSON);
        Person personInFilteredBefriendeeList =
            personStore.getFilteredBefriendeeList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());

        Person editedPerson = new PersonBuilder(personInFilteredBefriendeeList)
            .withName(CommandTestUtil.VALID_NAME_AMY)
            .build();

        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_AMY)
                .withRole(CommandTestUtil.VALID_ROLE_BEFRIENDEE)
                .build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        expectedPersonStore.setPerson(expectedPersonStore.getFilteredVolunteerList().get(1), editedPerson);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredVolunteerList_failure() {
        Index outOfBoundIndex =
            Index.fromOneBased(model.getDatastore().getPersonStore().getFilteredVolunteerList().size() + 1);
        EditCommand.EditPersonDescriptor descriptor =
            new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB)
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredBefriendeeList_failure() {
        Index outOfBoundIndex =
            Index.fromOneBased(model.getDatastore().getPersonStore().getFilteredBefriendeeList().size() + 1);
        EditCommand.EditPersonDescriptor descriptor =
            new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB)
                .withRole(CommandTestUtil.VALID_ROLE_BEFRIENDEE)
                .build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredVolunteerList_failure() {
        CommandTestUtil.showVolunteerAtIndex(personStore, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatastore().getPersonStore().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withRole(CommandTestUtil.VALID_ROLE_VOLUNTEER).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredBefriendeeList_failure() {
        CommandTestUtil.showBefriendeeAtIndex(personStore, TypicalIndexes.INDEX_FIRST_PERSON);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDatastore().getPersonStore().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB)
                .withRole(CommandTestUtil.VALID_ROLE_BEFRIENDEE).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand =
            new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, CommandTestUtil.DESC_AMY_VOLUNTEER);

        // same values -> returns true
        EditCommand.EditPersonDescriptor copyDescriptor =
            new EditCommand.EditPersonDescriptor(CommandTestUtil.DESC_AMY_VOLUNTEER);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(
            standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_PERSON,
                CommandTestUtil.DESC_AMY_VOLUNTEER)));

        // different descriptor -> returns false
        assertFalse(
            standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON,
                CommandTestUtil.DESC_BOB_BEFRIENDEE)));

    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(index, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
            + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
