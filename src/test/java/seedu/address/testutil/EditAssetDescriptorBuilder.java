package seedu.address.testutil;

import seedu.address.logic.commands.EditAssetCommand.EditAssetDescriptor;
import seedu.address.model.asset.Asset;
import seedu.address.model.person.fields.*;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditAssetDescriptorBuilder {

    private EditAssetDescriptor descriptor;

    public EditAssetDescriptorBuilder() {
        descriptor = new EditAssetDescriptor();
    }

    public EditAssetDescriptorBuilder(EditAssetDescriptor descriptor) {
        this.descriptor = new EditAssetDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditAssetDescriptorBuilder(Asset asset) {
        descriptor = new EditAssetDescriptor();
        descriptor.setName(new Name(asset.get()));
    }

    public EditAssetDescriptor build() {
        return descriptor;
    }
}
