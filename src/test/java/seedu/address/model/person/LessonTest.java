package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null));
    }

    @Test
    public void constructor_invalidLesson_throwsIllegalArgumentException() {
        String invalidLesson = "";
        assertThrows(IllegalArgumentException.class, () -> new Lesson(invalidLesson));
    }

    @Test
    public void isValidLesson() {
        // null lesson
        assertThrows(NullPointerException.class, () -> Lesson.isValidLesson(null));

        // invalid lessons
        assertFalse(Lesson.isValidLesson("")); // empty string
        assertFalse(Lesson.isValidLesson(" ")); // spaces only
        assertFalse(Lesson.isValidLesson("Math|invalidDate|09:00|0")); // invalid date format
        assertFalse(Lesson.isValidLesson("Math|01-01-2023|invalidTime|0")); // invalid time format
        assertFalse(Lesson.isValidLesson("Math|01-01-2023|09:00|2")); // invalid isCompleted value

        // valid lessons
        assertTrue(Lesson.isValidLesson("Math|01-01-2023|09:00|0"));
        assertTrue(Lesson.isValidLesson("Science|01-01-2023|10:00|1"));
    }

    @Test
    public void equals() {
        Lesson lesson = new Lesson("Math|01-01-2023|09:00|0");

        // same values -> returns true
        assertTrue(lesson.equals(new Lesson("Math|01-01-2023|09:00|0")));

        // same object -> returns true
        assertEquals(lesson, lesson);

        // null -> returns false
        assertFalse(lesson.equals(null));

        // different types -> returns false
        assertFalse(lesson.equals(5.0f));

        // different values -> returns false
        assertFalse(lesson.equals(new Lesson("Science|01-01-2023|09:00|0")));
    }
}
