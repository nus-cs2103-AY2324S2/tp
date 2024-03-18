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
import static seedu.address.logic.commands.CommandTestUtil.showStartupAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STARTUP;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditStartupDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.EditStartupDescriptorBuilder;
import seedu.address.testutil.StartupBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Startup editedStartup = new StartupBuilder().build();
        EditStartupDescriptor descriptor = new EditStartupDescriptorBuilder(editedStartup).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STARTUP, descriptor);

        String expectedMessage = String.format(
                EditCommand.MESSAGE_EDIT_STARTUP_SUCCESS, Messages.format(editedStartup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStartup(model.getFilteredStartupList().get(0), editedStartup);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStartup = Index.fromOneBased(model.getFilteredStartupList().size());
        Startup lastStartup = model.getFilteredStartupList().get(indexLastStartup.getZeroBased());

        StartupBuilder startupInList = new StartupBuilder(lastStartup);
        Startup editedStartup = startupInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditStartupDescriptor descriptor = new EditStartupDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastStartup, descriptor);

        String expectedMessage = String.format(
                EditCommand.MESSAGE_EDIT_STARTUP_SUCCESS, Messages.format(editedStartup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStartup(lastStartup, editedStartup);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STARTUP, new EditStartupDescriptor());
        Startup editedStartup = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());

        String expectedMessage = String.format(
                EditCommand.MESSAGE_EDIT_STARTUP_SUCCESS, Messages.format(editedStartup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStartupAtIndex(model, INDEX_FIRST_STARTUP);

        Startup startupInFilteredList = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        Startup editedStartup = new StartupBuilder(startupInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STARTUP,
                new EditStartupDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(
                EditCommand.MESSAGE_EDIT_STARTUP_SUCCESS, Messages.format(editedStartup));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStartup(model.getFilteredStartupList().get(0), editedStartup);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStartupUnfilteredList_failure() {
        Startup firstStartup = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        EditStartupDescriptor descriptor = new EditStartupDescriptorBuilder(firstStartup).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STARTUP, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STARTUP);
    }

    @Test
    public void execute_duplicateStartupFilteredList_failure() {
        showStartupAtIndex(model, INDEX_FIRST_STARTUP);

        // edit startup in filtered list into a duplicate in address book
        Startup startupInList = model.getAddressBook().getStartupList().get(INDEX_SECOND_STARTUP.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STARTUP,
                new EditStartupDescriptorBuilder(startupInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STARTUP);
    }

    @Test
    public void execute_invalidStartupIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStartupList().size() + 1);
        EditStartupDescriptor descriptor = new EditStartupDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStartupIndexFilteredList_failure() {
        showStartupAtIndex(model, INDEX_FIRST_STARTUP);
        Index outOfBoundIndex = INDEX_SECOND_STARTUP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStartupList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStartupDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_STARTUP, DESC_AMY);

        // same values -> returns true
        EditStartupDescriptor copyDescriptor = new EditStartupDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_STARTUP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_STARTUP, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_STARTUP, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditStartupDescriptor editStartupDescriptor = new EditStartupDescriptor();
        EditCommand editCommand = new EditCommand(index, editStartupDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editStartupDescriptor="
                + editStartupDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
