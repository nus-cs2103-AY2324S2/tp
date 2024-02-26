package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's interview date in the address book.
 */
public class InterviewDate {

    public final LocalDateTime value;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs an {@code interview date}.
     *
     * @param interviewDate A valid interview date.
     */
    public InterviewDate(String interviewDate) {
        if (interviewDate == null) {
            value = null;
        } else {
            value = LocalDateTime.parse(interviewDate, formatter);
        }
    }

    @Override
    public String toString() {
        String str = "";
        if (value != null) {
            str = value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        }
        return str;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        //        if (!(other instanceof InterviewDate)) {
        //            return false;
        //        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        InterviewDate otherInterviewDate = (InterviewDate) other;
        if (value == null) {
            return otherInterviewDate.value == null;
        }
        return value.equals(otherInterviewDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
