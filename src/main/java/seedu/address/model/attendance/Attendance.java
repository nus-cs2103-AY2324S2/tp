package seedu.address.model.attendance;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents the attendance of a student in TA Toolkit. The attendance of someone is marked negatively.
 */
public class Attendance {
    private final Status[] attendanceList;

    /**
     * An enum that represents the status of a week's attendance.
     */
    public enum Status {
        PRESENT, ABSENT
    }

    /**
     * Creates an Attendance object with all weeks unmarked.
     * By default, all weeks are marked as present since attendance is marked negatively.
     */
    public Attendance() {
        attendanceList = new Status[13];
        resetAttendance();
    }

    /**
     * Returns true if the student is absent for the specified week.
     * @param week The week to check the attendance status of
     */
    public boolean isAbsent(Week week) {
        return attendanceList[week.getWeekIndex().getZeroBased()].equals(Status.ABSENT);
    }

    /**
     * Changes the attendance status of a week. If the week is already modified, return false.
     * @param week The week to change the attendance status of
     * @param status The new status of the week
     */
    public void changeAttendanceStatus(Week week, Status status) {
        attendanceList[week.getWeekIndex().getZeroBased()] = status;
    }

    /**
     * Resets the attendance of the student to all present.
     * Attendance is marked negatively, hence all weeks are marked as present by default.
     */
    public void resetAttendance() {
        for (int i = 0; i < 13; i++) {
            attendanceList[i] = Status.PRESENT;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Weeks Absent: ");
        String absentWeeks = IntStream.rangeClosed(0, 12)
                .filter(i -> attendanceList[i] == Status.ABSENT)
                .map(i -> i + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
        sb.append(absentWeeks.isEmpty() ? "0" : absentWeeks);
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        for (int i = 0; i < 13; i++) {
            if (attendanceList[i] != otherAttendance.attendanceList[i]) {
                return false;
            }
        }
        return true;
    }
}
