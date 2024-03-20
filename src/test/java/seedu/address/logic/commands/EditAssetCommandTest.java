package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditAssetCommand.MESSAGE_EDIT_ASSET_SUCCESS;
import static seedu.address.logic.commands.EditAssetCommand.MESSAGE_NOT_EDITED;
import static seedu.address.model.person.fields.Tags.PREFIX_TAG;
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

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validEdit_success() throws CommandException {
        Asset assetToEdit = Asset.of("Laptop");
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
        Asset assetToEdit = Asset.of("Laptop");
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(assetToEdit).build();

        assertThrows(IllegalArgumentException.class, MESSAGE_NOT_EDITED, () ->
                new EditAssetCommand(assetToEdit, descriptor));
    }
}
