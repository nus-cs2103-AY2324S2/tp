package seedu.teachstack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.teachstack.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.teachstack.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_GROUP_GROUP1;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.teachstack.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import org.junit.jupiter.api.Test;

import seedu.teachstack.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.teachstack.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different studentId -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withStudentId(VALID_STUDENTID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different groups -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withGroups(VALID_GROUP_GROUP1).build();

        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", studentId="
                + editPersonDescriptor.getStudentId().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", grade="
                + editPersonDescriptor.getGrade().orElse(null) + ", groups="
                + editPersonDescriptor.getGroups().orElse(null) + "}";

        assertEquals(expected, editPersonDescriptor.toString());
    }
}
