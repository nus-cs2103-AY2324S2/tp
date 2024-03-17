package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  Logic to interpret date and time from user input
 */
public class InterviewTime {

    public static final String MESSAGE_CONSTRAINTS = "Error thrown, date and time format may be wrong. Check UG.";

    public static final String REGEX_YYYY = "\\d{4}";
    public static final String REGEX_DD = "(0[1-9]|[1-2][0-9]|3[01])";
    public static final String REGEX_MM = "(0[1-9]|1[0-2])";
    public static final String REGEX_HHMM = "^([0-1][0-9]|2[0-3])[0-5][0-9]$";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm"); //set format

    public final LocalDateTime dateTime;

    /**
     * Constructs InterviewTime object
     * @param dateTime input
     */
    public InterviewTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidInterviewTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, formatter); //set format
    }

    /**
     * Checks if date input is valid
     * @param test input
     * @return true if correct format, false otherwise
     */
    public static boolean isValidInterviewTime(String test) {
        if (test.length() != 12) {
            return false;
        } else {
            if (!test.substring(0, 2).matches(REGEX_DD) | !test.substring(2, 4).matches(REGEX_MM)) {
                return false;
            }
        }
        return true;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter beautify = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a", Locale.ENGLISH);
        return dateTime.format(beautify);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewTime)) {
            return false;
        }

        InterviewTime otherDateTime = (InterviewTime) other;
        return dateTime.equals(otherDateTime.dateTime);
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }


}
