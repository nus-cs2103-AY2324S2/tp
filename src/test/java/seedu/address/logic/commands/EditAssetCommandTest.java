package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_ASSET_DISPLAYED;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertParseFailure;
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
import seedu.address.model.person.fields.Name;
import seedu.address.testutil.AssetBuilder;
import seedu.address.testutil.EditAssetDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditAssetCommandTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssetCommand.MESSAGE_USAGE);
    private static final String MESSAGE_CONSTRAINT_NAME = "Names should only contain alphanumeric characters and "
            + "spaces, and it should not be blank";
    private final Asset asset1 = Asset.of("Laptop");
    private final Asset asset2 = Asset.of("Desktop");

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEdit_success() throws CommandException {
        Person personWithAsset = new PersonBuilder().withAssets(asset1.get()).build();
        model.addPerson(personWithAsset);
        Asset editedAsset = new AssetBuilder().build();
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(editedAsset).build();
        EditAssetCommand editCommand = new EditAssetCommand(asset1, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ASSET_SUCCESS, asset1.get());

        assertEquals(expectedMessage, editCommand.execute(model));
    }

    @Test
    public void execute_notEdited_throwsCommandException() {
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(asset1).build();

        assertThrows(IllegalArgumentException.class, MESSAGE_NOT_EDITED, () ->
                new EditAssetCommand(asset1, descriptor));
    }

    @Test
    public void execute_invalidAsset_throwsCommandException() {
        Person personWithAsset = new PersonBuilder().withAssets(asset1.get()).build();
        model.addPerson(personWithAsset);
        Asset invalidEditedAsset = new AssetBuilder().build();
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(asset1).build();
        EditAssetCommand editCommand = new EditAssetCommand(invalidEditedAsset, descriptor);

        assertThrows(CommandException.class, MESSAGE_INVALID_ASSET_DISPLAYED, () ->
                editCommand.execute(model));
    }

    @Test
    public void execute_emptyDescriptor_throwsCommandException() {
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(asset1).build();

        assertThrows(IllegalArgumentException.class, MESSAGE_CONSTRAINTS, () ->
                new EditAssetCommand(Asset.of(""), descriptor));
    }

    @Test
    public void execute_emptyAssetName_throwsCommandException() {
        EditAssetDescriptor descriptor = new EditAssetDescriptor();

        assertThrows(IllegalArgumentException.class, MESSAGE_CONSTRAINT_NAME, () ->
                descriptor.setName(new Name("")));
    }

    @Test
    public void execute_editAssetWithSameName_throwsCommandException() {
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(asset1).build();

        assertThrows(IllegalArgumentException.class, MESSAGE_NOT_EDITED, () ->
                new EditAssetCommand(asset1, descriptor));
    }

    @Test
    public void of_invalidInput_failure() {
        // missing field
        assertParseFailure(EditAssetCommand::of, "Laptop", MESSAGE_INVALID_FORMAT);
        // missing field
        assertParseFailure(EditAssetCommand::of, "", MESSAGE_INVALID_FORMAT);
        // null
        assertThrows(NullPointerException.class, () -> EditAssetCommand.of(null));
        // unedited
        assertThrows(IllegalArgumentException.class, () -> EditAssetCommand.of("Laptop Laptop"));
        // only alphanum allowed for name
        assertThrows(IllegalArgumentException.class, () -> EditAssetCommand.of("Laptop \uD83D\uDC4D"));
    }

    @Test
    public void of_validInput_success() {
        assertDoesNotThrow(() -> EditAssetCommand.of("Laptop Desktop"));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        EditAssetCommand editCommand = new EditAssetCommand(asset1, new EditAssetDescriptorBuilder(asset2).build());
        assertTrue(editCommand.equals(editCommand));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        EditAssetCommand editCommand1 = new EditAssetCommand(asset1, new EditAssetDescriptorBuilder(asset2).build());
        EditAssetCommand editCommand2 = new EditAssetCommand(asset1, new EditAssetDescriptorBuilder(asset2).build());
        assertTrue(editCommand1.equals(editCommand2));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        EditAssetCommand editCommand1 = new EditAssetCommand(asset1, new EditAssetDescriptorBuilder(asset2).build());
        EditAssetCommand editCommand2 = new EditAssetCommand(asset2, new EditAssetDescriptorBuilder(asset1).build());
        assertFalse(editCommand1.equals(editCommand2));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        EditAssetCommand editCommand = new EditAssetCommand(asset1, new EditAssetDescriptorBuilder(asset2).build());
        assertFalse(editCommand.equals(new Object()));
    }
}
