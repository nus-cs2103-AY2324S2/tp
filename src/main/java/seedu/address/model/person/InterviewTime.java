package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *  Logic to interpret date and time from user input
 */
public class InterviewTime {

    public static final String MESSAGE_CONSTRAINTS = "Error thrown, date and time format may be wrong. Check UG.";

    public static final String VALIDATION_REGEX = "[^\\s].*";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm"); //set format

    public final LocalDateTime dateTime;

    public InterviewTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValid(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, formatter); //set format
    }

    public static boolean isValid(String test) {
        try {
            LocalDateTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
