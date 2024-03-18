package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_SUPPLIER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_SUPPLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.testutil.EditSupplierDescriptorBuilder;

public class EditSupplierDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditSupplierDescriptor descriptorWithSameValues = new EditSupplierDescriptor(DESC_AMY_SUPPLIER);
        assertTrue(DESC_AMY_SUPPLIER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_SUPPLIER.equals(DESC_AMY_SUPPLIER));

        // null -> returns false
        assertFalse(DESC_AMY_SUPPLIER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_SUPPLIER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_SUPPLIER.equals(DESC_BOB_SUPPLIER));

        // different phone -> returns false
        EditSupplierDescriptor editedAmy =
                new EditSupplierDescriptorBuilder(DESC_AMY_SUPPLIER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_SUPPLIER.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY_SUPPLIER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY_SUPPLIER.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY_SUPPLIER).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY_SUPPLIER.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditSupplierDescriptorBuilder(DESC_AMY_SUPPLIER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY_SUPPLIER.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditSupplierDescriptor editSupplierDescriptor = new EditSupplierDescriptor();
        String expected = EditSupplierDescriptor.class.getCanonicalName() + "{phone="
                + editSupplierDescriptor.getPhone().orElse(null) + ", email="
                + editSupplierDescriptor.getEmail().orElse(null) + ", address="
                + editSupplierDescriptor.getAddress().orElse(null) + ", tags="
                + editSupplierDescriptor.getTags().orElse(null) + ", product="
                + editSupplierDescriptor.getProduct().orElse(null) + ", price="
                + editSupplierDescriptor.getPrice().orElse(null) + "}";
        assertEquals(expected, editSupplierDescriptor.toString());
    }
}
