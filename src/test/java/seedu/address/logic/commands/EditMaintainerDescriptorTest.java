package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_MAINTAINER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_MAINTAINER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMaintainerCommand.EditMaintainerDescriptor;
import seedu.address.testutil.EditMaintainerDescriptorBuilder;

public class EditMaintainerDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMaintainerDescriptor descriptorWithSameValues = new EditMaintainerDescriptor(DESC_AMY_MAINTAINER);
        assertTrue(DESC_AMY_MAINTAINER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_MAINTAINER.equals(DESC_AMY_MAINTAINER));

        // null -> returns false
        assertFalse(DESC_AMY_MAINTAINER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_MAINTAINER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_MAINTAINER.equals(DESC_BOB_MAINTAINER));

        // different phone -> returns false
        EditMaintainerDescriptor editedAmy =
                new EditMaintainerDescriptorBuilder(DESC_AMY_MAINTAINER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_MAINTAINER.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditMaintainerDescriptorBuilder(DESC_AMY_MAINTAINER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY_MAINTAINER.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditMaintainerDescriptorBuilder(DESC_AMY_MAINTAINER).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY_MAINTAINER.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditMaintainerDescriptorBuilder(DESC_AMY_MAINTAINER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY_MAINTAINER.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditMaintainerDescriptor editMaintainerDescriptor = new EditMaintainerDescriptor();
        String expected = EditMaintainerDescriptor.class.getCanonicalName() + "{phone="
                + editMaintainerDescriptor.getPhone().orElse(null) + ", email="
                + editMaintainerDescriptor.getEmail().orElse(null) + ", address="
                + editMaintainerDescriptor.getAddress().orElse(null) + ", tags="
                + editMaintainerDescriptor.getTags().orElse(null) + ", skill="
                + editMaintainerDescriptor.getSkill().orElse(null) + ", commission="
                + editMaintainerDescriptor.getCommission().orElse(null) + "}";
        assertEquals(expected, editMaintainerDescriptor.toString());
    }
}
