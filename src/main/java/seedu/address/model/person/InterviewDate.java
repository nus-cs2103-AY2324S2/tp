package seedu.address.model.person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInterviewDate(String)}
 */
public class InterviewDate {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the interview date must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final LocalDateTime value;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs an {@code interview date}.
     *
     * @param interviewDate A valid interview date.
     */
    public InterviewDate(String interviewDate) {
        checkArgument(isValidInterviewDate(interviewDate), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(interviewDate, formatter);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidInterviewDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {

        return value.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewDate)) {
            return false;
        }

        InterviewDate otherInterviewDate = (InterviewDate) other;
        return value.equals(otherInterviewDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
