package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCommand.EditCompanyDescriptor(DESC_AMY);
     ;
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
        EditCommand.EditCompanyDescriptor editedAmy = new EditCompanyDescriptorBuilder(
                DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));



        // different tags -> returns false
        editedAmy = new EditCompanyDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCompanyDescriptor editCompanyDescriptor = new EditCompanyDescriptor();
        String expected = EditCommand.EditCompanyDescriptor.class.getCanonicalName() + "{name="
                + editCompanyDescriptor.getName().orElse(null) + ", phone="
                + editCompanyDescriptor.getPhone().orElse(null) + ", email="
                + editCompanyDescriptor.getEmail().orElse(null) + ", tags="
                + editCompanyDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editCompanyDescriptor.toString());
    }
}
