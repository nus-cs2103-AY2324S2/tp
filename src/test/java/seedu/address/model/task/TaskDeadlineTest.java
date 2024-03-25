package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {
    public static final DateTimeFormatter VALIDATION_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDeadline(null));
    }

    @Test
    public void constructor_invalidTaskDeadline_throwsIllegalArgumentException() {
        String invalidTaskDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDeadline(invalidTaskDeadline));
    }

    @Test
    public void toStringTest() {
        String date = "12-12-2024 16:00";
        LocalDateTime dateTime = LocalDateTime.parse(date, VALIDATION_FORMAT);
        TaskDeadline taskDeadline = new TaskDeadline(date);
        assertEquals(dateTime.format(OUTPUT_FORMAT), taskDeadline.toString());
    }

    @Test
    public void toJsonSaveTest() {
        String date = "12-12-2024 16:00";
        LocalDateTime dateTime = LocalDateTime.parse(date, VALIDATION_FORMAT);
        TaskDeadline taskDeadline = new TaskDeadline(date);
        assertEquals(dateTime.format(VALIDATION_FORMAT), taskDeadline.toJsonSave());
    }

    @Test
    public void isValidTaskDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> TaskDeadline.isValidTaskDeadline(null));

        // invalid deadline
        assertFalse(TaskDeadline.isValidTaskDeadline("")); // empty string
        assertFalse(TaskDeadline.isValidTaskDeadline(" ")); // spaces only
        assertFalse(TaskDeadline.isValidTaskDeadline("12/12/2024 16:00")); // Wrong format
        assertFalse(TaskDeadline.isValidTaskDeadline("12-12-2024")); // Missing time
        assertFalse(TaskDeadline.isValidTaskDeadline("12-12-2024 6:00")); // Missing digits for time

        // valid deadline
        assertTrue(TaskDeadline.isValidTaskDeadline("12-12-2024 16:00")); // Valid format
    }

    @Test
    public void equals() {
        TaskDeadline taskDeadline = new TaskDeadline("12-12-2024 16:00");

        // same values -> returns true
        assertTrue(taskDeadline.equals(new TaskDeadline("12-12-2024 16:00")));

        // same object -> returns true
        assertTrue(taskDeadline.equals(taskDeadline));

        // null -> returns false
        assertFalse(taskDeadline.equals(null));

        // different types -> returns false
        assertFalse(taskDeadline.equals(5.0f));

        // different values -> returns false
        assertFalse(taskDeadline.equals(new TaskDeadline("12-12-2024 18:00")));
    }

    @Test
    public void hashCodeTest() {
        TaskDeadline taskDeadline = new TaskDeadline("12-12-2024 16:00");

        assertEquals(taskDeadline.hashCode(), taskDeadline.taskDeadline.hashCode());
    }
}
