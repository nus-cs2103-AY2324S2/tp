package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidAttendanceDate_throwsIllegalArgumentException() {
        String invalidAttendanceDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendanceDate));
    }

    @Test
    public void isValidDate() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Attendance.isValidDate(null));
    }

}
