package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSET_DISPLAYED;
import static seedu.address.logic.commands.EditAssetCommand.MESSAGE_EDIT_ASSET_SUCCESS;
import static seedu.address.logic.commands.EditAssetCommand.MESSAGE_NOT_EDITED;
import static seedu.address.model.asset.Asset.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAssetCommand.EditAssetDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.asset.Asset;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssetBuilder;
import seedu.address.testutil.EditAssetDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditAssetCommandTest {

    private final Asset assetToEdit = Asset.of("Laptop");

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEdit_success() throws CommandException {
        Person personWithAsset = new PersonBuilder().withAssets(assetToEdit.get()).build();
        model.addPerson(personWithAsset);
        Asset editedAsset = new AssetBuilder().build();
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(editedAsset).build();
        EditAssetCommand editCommand = new EditAssetCommand(assetToEdit, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ASSET_SUCCESS, assetToEdit.get());

        assertEquals(expectedMessage, editCommand.execute(model));
    }

    @Test
    public void execute_notEdited_throwsCommandException() {
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(assetToEdit).build();

        assertThrows(IllegalArgumentException.class, MESSAGE_NOT_EDITED, () ->
                new EditAssetCommand(assetToEdit, descriptor));
    }

    @Test
    public void execute_invalidAsset_throwsCommandException() {
        Person personWithAsset = new PersonBuilder().withAssets(assetToEdit.get()).build();
        model.addPerson(personWithAsset);
        Asset invalidEditedAsset = new AssetBuilder().build();
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(assetToEdit).build();
        EditAssetCommand editCommand = new EditAssetCommand(invalidEditedAsset, descriptor);

        assertThrows(CommandException.class, MESSAGE_INVALID_ASSET_DISPLAYED, () ->
                editCommand.execute(model));
    }

    @Test
    public void execute_emptyDescriptor_throwsCommandException() {
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(assetToEdit).build();

        assertThrows(IllegalArgumentException.class, MESSAGE_CONSTRAINTS, () ->
                new EditAssetCommand(Asset.of(""), descriptor));
    }

    @Test
    public void equals() {
        Asset asset1 = Asset.of("Laptop");
        Asset asset2 = Asset.of("Desktop");
        EditAssetDescriptor descriptor1 = new EditAssetDescriptorBuilder(asset1).build();
        EditAssetDescriptor descriptor2 = new EditAssetDescriptorBuilder(asset2).build();

        EditAssetCommand editCommand1 = new EditAssetCommand(asset1, descriptor2);
        EditAssetCommand editCommand2 = new EditAssetCommand(asset1, descriptor2);
        EditAssetCommand editCommand3 = new EditAssetCommand(asset2, descriptor1);
        EditAssetCommand editCommand4 = new EditAssetCommand(asset2, descriptor1);

        // Same object -> returns true
        assertTrue(editCommand1.equals(editCommand1));

        // Same values -> returns true
        assertTrue(editCommand1.equals(editCommand2));

        // Different types -> returns false
        assertFalse(editCommand1.equals(1));

        // null -> returns false
        assertFalse(editCommand1.equals(null));

        // Different command -> returns false
        assertFalse(editCommand1.equals(editCommand3));
        assertFalse(editCommand1.equals(editCommand4));
    }
}
