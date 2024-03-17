package educonnect.logic.commands;

import static educonnect.logic.commands.CommandTestUtil.DESC_AMY;
import static educonnect.logic.commands.CommandTestUtil.DESC_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static educonnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static educonnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_BOB;
import static educonnect.testutil.TypicalTimetableAndValues.VALID_TIMETABLE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import educonnect.logic.commands.EditCommand.EditStudentDescriptor;
import educonnect.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_AMY);
        assertEquals(DESC_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_AMY, DESC_AMY);

        // null -> returns false
        assertNotEquals(null, DESC_AMY);

        // different types -> returns false
        assertNotEquals(5, DESC_AMY);

        // different values -> returns false
        assertNotEquals(DESC_AMY, DESC_BOB);

        // different name -> returns false
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withStudentId(VALID_STUDENT_ID_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different address -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withTelegramHandle(VALID_TELEGRAM_HANDLE_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different timetable -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withTimetable(VALID_TIMETABLE_1).build();
        assertNotEquals(DESC_AMY, editedAmy);
    }

    @Test
    public void toStringMethod() {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        String expected = EditStudentDescriptor.class.getCanonicalName()
                          + "{name=" + editStudentDescriptor.getName().orElse(null)
                          + ", student id=" + editStudentDescriptor.getStudentId().orElse(null)
                          + ", email=" + editStudentDescriptor.getEmail().orElse(null)
                          + ", telegram handle=" + editStudentDescriptor.getTelegramHandle().orElse(null)
                          + ", tags=" + editStudentDescriptor.getTags().orElse(null)
                          + ", timetable=" + editStudentDescriptor.getTimetable().orElse(null)+ "}";
        assertEquals(expected, editStudentDescriptor.toString());
    }
}
