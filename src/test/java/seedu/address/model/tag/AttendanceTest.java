package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.AttendanceStatus;

public class AttendanceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidAttendanceDate_throwsIllegalArgumentException() {
        String invalidAttendanceDate = "32-02-2024";
        String invalidAttendanceStatus = "3";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(
                new AttendanceStatus(invalidAttendanceDate, invalidAttendanceStatus)));
    }

    @Test
    public void isValidDate() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Attendance.isValidDate(null));
    }

}
