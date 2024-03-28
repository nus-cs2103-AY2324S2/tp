package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateCommand.UpdatePersonDescriptor;
import seedu.address.testutil.UpdatePersonDescriptorBuilder;

public class UpdatePersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdatePersonDescriptor descriptorWithSameValues = new UpdatePersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        UpdatePersonDescriptor updatedAmy =
                new UpdatePersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(updatedAmy));

        // different phone -> returns false
        updatedAmy = new UpdatePersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(updatedAmy));

        // different address -> returns false
        updatedAmy = new UpdatePersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(updatedAmy));

        // different tags -> returns false
        updatedAmy = new UpdatePersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(updatedAmy));
    }

    @Test
    public void toStringMethod() {
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptor();
        String expected = UpdatePersonDescriptor.class.getCanonicalName() + "{nric="
                + updatePersonDescriptor.getNric() + ", name="
                + updatePersonDescriptor.getName().orElse(null) + ", phone="
                + updatePersonDescriptor.getPhone().orElse(null) + ", address="
                + updatePersonDescriptor.getAddress().orElse(null) + ", date of birth="
                + updatePersonDescriptor.getDateOfBirth().orElse(null) + ", sex="
                + updatePersonDescriptor.getSex().orElse(null) + ", status="
                + updatePersonDescriptor.getStatus().orElse(null) + "}";
        assertEquals(expected, updatePersonDescriptor.toString());
    }
}
