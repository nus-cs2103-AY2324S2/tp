package seedu.address.logic.commands;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.fields.Tags.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditAssetCommand.EditAssetDescriptor;
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
    public void execute_success() {
        Person editedPerson = new PersonBuilder().build();
        Asset editedAsset = new AssetBuilder().build();
        EditAssetDescriptor descriptor = new EditAssetDescriptorBuilder(editedAsset).build();
        EditAssetCommand editCommand = new EditAssetCommand(editedAsset, descriptor);

        String expectedMessage = String.format(EditAssetCommand.MESSAGE_EDIT_ASSET_SUCCESS,
                Messages.format(editedPerson));

        //Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        //expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        //
        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
