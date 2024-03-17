package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing supplier in the address book.
 */
public class EditSupplierCommand extends Command {

    public static final String COMMAND_WORD = "/edit-supplier";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the supplier identified "
            + "by the name used in the displayed person list.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_FIELD + "FIELD] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_PRODUCT + "PRODUCT] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "Example: " + COMMAND_WORD
            + PREFIX_NAME + "John Doe Others "
            + PREFIX_FIELD + "{ "
            + PREFIX_NAME + "John Deed "
            + PREFIX_ADDRESS + "NUS College Avenue"
            + " }";

    public static final String MESSAGE_EDIT_SUPPLIER_SUCCESS = "Edited Supplier: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "This supplier's name already exists in the address book.";

    private final Name name;
    private final EditSupplierDescriptor editSupplierDescriptor;

    /**
     * @param name of the supplier in the filtered person list to edit
     * @param editSupplierDescriptor details to edit the supplier with
     */
    public EditSupplierCommand(Name name, EditSupplierDescriptor editSupplierDescriptor) {
        requireNonNull(name);
        requireNonNull(editSupplierDescriptor);

        this.name = name;
        this.editSupplierDescriptor = new EditSupplierDescriptor(editSupplierDescriptor);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Supplier supplierToEdit = model.findSupplierByName(name);

        if (supplierToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Supplier editedSupplier = createEditedSupplier(supplierToEdit, editSupplierDescriptor);

        if (!supplierToEdit.isSamePerson(editedSupplier) && model.hasPerson(editedSupplier)) {
            throw new CommandException(MESSAGE_DUPLICATE_SUPPLIER);
        }

        model.setPerson(supplierToEdit, editedSupplier);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_SUPPLIER_SUCCESS, Messages.format(editedSupplier)));
    }

    /**
     * Creates and returns a {@code Supplier} with the details of {@code supplierToEdit}
     * edited with {@code editSupplierDescriptor}.
     */
    private static Supplier createEditedSupplier(Supplier supplierToEdit,
            EditSupplierDescriptor editSupplierDescriptor) {
        assert supplierToEdit != null;

        Name updatedName = editSupplierDescriptor.getName().orElse(supplierToEdit.getName());
        Phone updatedPhone = editSupplierDescriptor.getPhone().orElse(supplierToEdit.getPhone());
        Email updatedEmail = editSupplierDescriptor.getEmail().orElse(supplierToEdit.getEmail());
        Address updatedAddress = editSupplierDescriptor.getAddress().orElse(supplierToEdit.getAddress());
        Set<Tag> updatedTags = editSupplierDescriptor.getTags().orElse(supplierToEdit.getTags());
        Product updatedProduct = editSupplierDescriptor.getProduct().orElse(supplierToEdit.getProduct());
        Price updatedPrice = editSupplierDescriptor.getPrice().orElse(supplierToEdit.getPrice());

        return new Supplier(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedProduct, updatedPrice);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditSupplierCommand otherEditCommand = (EditSupplierCommand) other;
        return name.equals(otherEditCommand.name)
                && editSupplierDescriptor.equals(otherEditCommand.editSupplierDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("editSupplierDescriptor", editSupplierDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the supplier with. Each non-empty field value will replace the
     * corresponding field value of the supplier.
     */
    public static class EditSupplierDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Product product;
        private Price price;

        public EditSupplierDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSupplierDescriptor(EditSupplierDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setProduct(toCopy.product);
            setPrice(toCopy.price);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, product, price);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public Optional<Product> getProduct() {
            return Optional.ofNullable(product);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSupplierDescriptor)) {
                return false;
            }

            EditSupplierDescriptor otherEditSupplierDescriptor = (EditSupplierDescriptor) other;

            // Compare each field individually
            boolean phoneEquals = Objects.equals(phone, otherEditSupplierDescriptor.phone);
            boolean emailEquals = Objects.equals(email, otherEditSupplierDescriptor.email);
            boolean addressEquals = Objects.equals(address, otherEditSupplierDescriptor.address);
            boolean tagsEquals = Objects.equals(tags, otherEditSupplierDescriptor.tags);
            boolean productEquals = Objects.equals(tags, otherEditSupplierDescriptor.tags);
            boolean priceEquals = Objects.equals(tags, otherEditSupplierDescriptor.tags);

            return phoneEquals && emailEquals && addressEquals && tagsEquals && productEquals && priceEquals;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("product", product)
                    .add("price", product)
                    .toString();
        }
    }
}
