package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidAttendance_throwsIllegalArgumentException() {
        String invalidAttendance = "On leave";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendance));
    }

    @Test
    public void isValidAttendance() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Attendance.isValidAttendance(null));

        // invalid attendance
        assertFalse(Attendance.isValidAttendance("On leave")); // Invalid status

        // valid attendance
        assertTrue(Attendance.isValidAttendance("Present"));
        assertTrue(Attendance.isValidAttendance("Absent"));
    }

    @Test
    public void equals() {
        Attendance attendance = new Attendance("Present");

        // same values -> returns true
        assertTrue(attendance.equals(new Attendance("Present")));

        // same object -> returns true
        assertTrue(attendance.equals(attendance));

        // null -> returns false
        assertFalse(attendance.equals(null));

        // different types -> returns false
        assertFalse(attendance.equals(5.0f));

        // different values -> returns false
        assertFalse(attendance.equals(new Attendance("Absent")));
    }
}
