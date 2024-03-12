package seedu.address.model.person.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NoteTest {
    @Nested
    class ConstructorTests {
        @Test
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Note(null, null));
        }

        @Test
        public void constructor_success_createsInstance() {
            LocalDateTime dateTime = LocalDateTime.now();
            Description description = new Description("Some description");
            Note note = new Note(dateTime, description);
            assertNotNull(note);
            assertEquals(dateTime, note.getDateTime());
            assertEquals(description, note.getDescription());
        }
    }

    @Nested
    class EqualsTests {
        @Test
        public void equals_same() {
            LocalDateTime dateTime = LocalDateTime.now();
            Description description = new Description("Test description");
            Note note1 = new Note(dateTime, description);
            Note note2 = new Note(dateTime, description);
            assertEquals(note1, note2);
        }

        @Test
        public void equals_differentDateTime() {
            Description description = new Description("Test description");
            Note note1 = new Note(LocalDateTime.now(), description);
            Note note2 = new Note(LocalDateTime.now().plusDays(1), description);
            assertNotEquals(note1, note2);
        }

        @Test
        public void equals_differentDescription() {
            LocalDateTime dateTime = LocalDateTime.now();
            Note note1 = new Note(dateTime, new Description("Description 1"));
            Note note2 = new Note(dateTime, new Description("Description 2"));
            assertNotEquals(note1, note2);
        }
    }

    @Nested
    class HashCodeTests {
        @Test
        public void hashCode_same() {
            LocalDateTime dateTime = LocalDateTime.now();
            Description description = new Description("Test description");
            Note note1 = new Note(dateTime, description);
            Note note2 = new Note(dateTime, description);
            assertEquals(note1.hashCode(), note2.hashCode());
        }


        @Test
        public void hashCode_different() {
            LocalDateTime dateTime = LocalDateTime.now();
            Note note1 = new Note(dateTime, new Description("Test description"));
            Note note2 = new Note(dateTime, new Description("Another description"));

            assertNotEquals(note1.hashCode(), note2.hashCode());
        }
    }

    @Nested
    class ToStringTests {
        @Test
        public void toString_success() {
            LocalDateTime dateTime = LocalDateTime.of(2023, 4, 20, 15, 30);
            Description description = new Description("Appointment at clinic");
            Note note = new Note(dateTime, description);
            String expected =
                String.format("seedu.address.model.person.note.Note{dateTime=%s, description=%s}", dateTime,
                    description);

            assertEquals(expected, note.toString());
        }
    }
}
