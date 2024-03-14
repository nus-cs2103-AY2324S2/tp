package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCourseMateDescriptor;
import seedu.address.testutil.EditCourseMateDescriptorBuilder;

public class EditCourseMateDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCourseMateDescriptor descriptorWithSameValues = new EditCourseMateDescriptor(DESC_AMY);
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
        EditCommand.EditCourseMateDescriptor editedAmy =
                new EditCourseMateDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditCourseMateDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditCourseMateDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different skills -> returns false
        editedAmy = new EditCourseMateDescriptorBuilder(DESC_AMY).withSkills(VALID_SKILL_JAVA).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditCourseMateDescriptor editCourseMateDescriptor = new EditCommand.EditCourseMateDescriptor();
        String expected = EditCommand.EditCourseMateDescriptor.class.getCanonicalName() + "{name="
                + editCourseMateDescriptor.getName().orElse(null) + ", phone="
                + editCourseMateDescriptor.getPhone().orElse(null) + ", email="
                + editCourseMateDescriptor.getEmail().orElse(null) + ", skills="
                + editCourseMateDescriptor.getSkills().orElse(null) + "}";
        assertEquals(expected, editCourseMateDescriptor.toString());
    }
}
