package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scrolls.elder.logic.commands.CommandTestUtil.DESC_AMY_VOLUNTEER;
import static scrolls.elder.logic.commands.CommandTestUtil.DESC_BOB_BEFRIENDEE;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import scrolls.elder.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditPersonDescriptor descriptorWithSameValues =
                new EditCommand.EditPersonDescriptor(DESC_AMY_VOLUNTEER);
        assertTrue(DESC_AMY_VOLUNTEER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_VOLUNTEER.equals(DESC_AMY_VOLUNTEER));

        // null -> returns false
        assertFalse(DESC_AMY_VOLUNTEER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_VOLUNTEER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_VOLUNTEER.equals(DESC_BOB_BEFRIENDEE));

        // different name -> returns false
        EditCommand.EditPersonDescriptor editedAmy =
                new EditPersonDescriptorBuilder(DESC_AMY_VOLUNTEER).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY_VOLUNTEER.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY_VOLUNTEER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_VOLUNTEER.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY_VOLUNTEER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY_VOLUNTEER.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY_VOLUNTEER).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY_VOLUNTEER.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY_VOLUNTEER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY_VOLUNTEER.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        String expected = EditCommand.EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phone="
                + editPersonDescriptor.getPhone().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", address="
                + editPersonDescriptor.getAddress().orElse(null) + ", tags="
                + editPersonDescriptor.getTags().orElse(null) + ", role="
                + editPersonDescriptor.getRole().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
