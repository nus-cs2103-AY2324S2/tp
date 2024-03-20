package seedu.address.commons.core.studentid;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a student ID, which is a 5-digit integer.
 */
public class StudentId {
    private final String studentId;

    /**
     * Creates a new {@code StudentId} with the specified student ID.
     */
    public StudentId(String studentId) {
        if (!isValidStudentId(studentId)) {
            throw new IllegalArgumentException("Student ID must be a 5-digit integer");
        }
        this.studentId = studentId;
    }

    /**
     * Returns the student ID as a string.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Checks if the provided student ID is a valid 5-digit integer.
     */
    private boolean isValidStudentId(String studentId) {
        // Check if the string consists of exactly 5 digits
        return studentId != null && studentId.matches("\\d{5}");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherStudentId = (StudentId) other;
        return studentId.equals(otherStudentId.studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("studentId", studentId).toString();
    }
}
