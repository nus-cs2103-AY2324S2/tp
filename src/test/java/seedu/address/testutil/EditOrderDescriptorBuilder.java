package seedu.address.testutil;

import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.model.order.Product;
import seedu.address.model.order.Quantity;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {
    private EditOrderCommand.EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
    }

    public EditOrderDescriptorBuilder(EditOrderCommand.EditOrderDescriptor descriptor) {
        this.descriptor = new EditOrderCommand.EditOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditOrderDescriptorBuilder(Product product, Quantity quantity) {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
        descriptor.setProduct(product);
        descriptor.setQuantity(quantity);
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withProduct(String product) {
        descriptor.setProduct(new Product(product));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withQuantity(int quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    public EditOrderCommand.EditOrderDescriptor build() {
        return descriptor;
    }
}
