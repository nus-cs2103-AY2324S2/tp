package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOTE2;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;
import seedu.address.testutil.EditNoteDescriptorBuilder;

public class EditNoteDescriptorTest {

    @Nested
    class FieldTests {
        @Test
        void setAndGetDateTime() {
            LocalDateTime testDateTime = LocalDateTime.of(2023, 10, 5, 12, 0);
            EditNoteDescriptor descriptor = new EditNoteDescriptor();
            descriptor.setDateTime(testDateTime);

            assertEquals(Optional.of(testDateTime), descriptor.getDateTime());
        }

        @Test
        void setAndGetDescription() {
            Description testDescription = new Description("Test description");
            EditNoteDescriptor descriptor = new EditNoteDescriptor();
            descriptor.setDescription(testDescription);

            assertEquals(Optional.of(testDescription), descriptor.getDescription());
        }
    }

    @Test
    public void equals() {
        EditNoteCommand.EditNoteDescriptor firstDescriptor = new EditNoteCommand.EditNoteDescriptor(DESC_NOTE1);
        EditNoteCommand.EditNoteDescriptor secondDescriptor = new EditNoteCommand.EditNoteDescriptor(DESC_NOTE1);
        EditNoteCommand.EditNoteDescriptor differentDescriptor = new EditNoteCommand.EditNoteDescriptor(DESC_NOTE2);

        // same values -> returns true
        assertTrue(firstDescriptor.equals(secondDescriptor));

        // same object -> returns true
        assertTrue(firstDescriptor.equals(firstDescriptor));

        // null -> returns false
        assertFalse(firstDescriptor.equals(null));

        // different types -> returns false
        assertFalse(firstDescriptor.equals(new String("Not a descriptor")));

        // different values -> returns false
        assertFalse(firstDescriptor.equals(differentDescriptor));
    }

    @Test
    public void toStringMethod() {
        LocalDateTime dateTime = LocalDateTime.parse("2024-02-19T11:30");
        Note note = new Note(dateTime, new Description("General Flu"));
        EditNoteDescriptor descriptor = new EditNoteDescriptorBuilder(note).build();

        String expected = EditNoteDescriptor.class.getCanonicalName() + "{dateTime=2024-02-19T11:30,"
                + " description=General Flu}";
        assertEquals(expected, descriptor.toString());
    }
}
