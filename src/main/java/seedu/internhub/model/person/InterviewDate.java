package seedu.internhub.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Person's interview date in the address book.
 */
public class InterviewDate {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public static final String MESSAGE_CONSTRAINTS =
            "Interview Date & Time needs to follow this pattern : dd-mm-yyyy HHmm\n"
                    + "Interview Date & Time cannot be before today's date !";
    public final LocalDateTime value;
    /**
     * Constructs an {@code interview date}.
     *
     * @param interviewDate A valid interview date.
     */
    public InterviewDate(String interviewDate) {
        if (interviewDate == null) {
            value = null;
        } else {
            value = LocalDateTime.parse(interviewDate, DATE_FORMAT);
        }
    }
    /**
     * Checks if the entered interview date follows the format
     */
    public static boolean isValidDate(String test) {
        try {
            // Check the format of the interview date by parse
            LocalDateTime inputDate = LocalDateTime.parse(test, DATE_FORMAT);
            LocalDateTime currentDate = LocalDateTime.now();
            // Set the time part of currentDate to 00:00
            currentDate = currentDate.withHour(0).withMinute(0).withSecond(0);
            return inputDate.isAfter(currentDate);
        } catch (DateTimeParseException e) {
            return false;
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

    /**
     * Checks if this InterviewDate is in the next N days.
     */

    public boolean isWithinNDays(int numberOfDays) {
        if (value == null) {
            return false;
        }
        // Get the current date and time
        LocalDateTime currentDate = LocalDateTime.now();

        // Calculate the difference in days between the interview date and the current date
        long daysDifference = ChronoUnit.DAYS.between(currentDate, value);
        // Check if the difference is less than or equal to 3
        return daysDifference > 0 && daysDifference <= numberOfDays;
    }

    //    public int compareTo(InterviewDate otherInterviewDate) {
    //        // Assuming you want to compare interview dates directly
    //        return value.compareTo(otherInterviewDate.value);
    //    }

}
