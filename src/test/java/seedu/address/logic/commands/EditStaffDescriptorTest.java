package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_STAFF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.testutil.EditStaffDescriptorBuilder;

public class EditStaffDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStaffDescriptor descriptorWithSameValues = new EditStaffDescriptor(DESC_AMY_STAFF);
        assertTrue(DESC_AMY_STAFF.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_STAFF.equals(DESC_AMY_STAFF));

        // null -> returns false
        assertFalse(DESC_AMY_STAFF.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_STAFF.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_STAFF.equals(DESC_BOB_STAFF));

        // different phone -> returns false
        EditStaffDescriptor editedAmy =
                new EditStaffDescriptorBuilder(DESC_AMY_STAFF).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_STAFF.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStaffDescriptorBuilder(DESC_AMY_STAFF).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY_STAFF.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditStaffDescriptorBuilder(DESC_AMY_STAFF).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY_STAFF.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStaffDescriptorBuilder(DESC_AMY_STAFF).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY_STAFF.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptor();
        String expected = EditStaffDescriptor.class.getCanonicalName() + "{phone="
                + editStaffDescriptor.getPhone().orElse(null) + ", email="
                + editStaffDescriptor.getEmail().orElse(null) + ", address="
                + editStaffDescriptor.getAddress().orElse(null) + ", tags="
                + editStaffDescriptor.getTags().orElse(null) + ", salary="
                + editStaffDescriptor.getSalary().orElse(null) + ", employment="
                + editStaffDescriptor.getEmployment().orElse(null) + "}";
        assertEquals(expected, editStaffDescriptor.toString());
    }
}
