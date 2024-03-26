package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

public class AttendanceTest {

    @Test
    public void isAbsentMethod() {
        Attendance a = new Attendance();
        a.changeAttendanceStatus(new Week(Index.fromOneBased(1)), Attendance.Status.ABSENT);
        assertTrue(a.isAbsent(new Week(Index.fromOneBased(1))));
    }

    @Test
    public void resetAttendanceMethod() {
        Attendance a = new Attendance();
        a.changeAttendanceStatus(new Week(Index.fromOneBased(1)), Attendance.Status.ABSENT);
        a.resetAttendance();
        assertFalse(a.isAbsent(new Week(Index.fromOneBased(1))));
    }

    @Test
    public void toStringMethod() {
        Attendance a = new Attendance();
        a.changeAttendanceStatus(new Week(Index.fromOneBased(1)), Attendance.Status.ABSENT);
        String expected = "Weeks Absent: 1";
        assertEquals(expected, a.toString());
    }

    @Test
    public void equals() {
        Attendance a1 = new Attendance();
        Attendance a2 = new Attendance();

        // same values -> returns true
        assertEquals(a1, a2);

        // same object -> returns true
        assertEquals(a1, a1);

        // null -> returns false
        assertNotEquals(null, a1);

        // different types -> returns false
        assertNotEquals(a1, 5.0f);

        // different values -> returns false
        a2.changeAttendanceStatus(new Week(Index.fromOneBased(1)), Attendance.Status.ABSENT);
        assertFalse(a1.equals(a2));
    }
}
