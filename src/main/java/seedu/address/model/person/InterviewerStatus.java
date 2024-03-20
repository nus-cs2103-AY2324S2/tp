package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.enums.InterviewerState;

/**
 * Represents an Interviewer's status in the Tether.
 * Guarantee: is valid as declared in {@link #matchStatus(String, String)}
 */
public class InterviewerStatus extends Status {
    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be either \"free\" or \"interview with [APPLICANT NAME]\"";

    public final String value;

    /**
     * Constructs a {@code ApplicantStatus}.
     *
     * @param status A status.
     */
    public InterviewerStatus(String status) {
        requireNonNull(status);
        value = matchStatus(status.toLowerCase(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks and returns the status if it is valid.
     */
    public String matchStatus(String status, String message) {
        Pattern patternFree = Pattern.compile("^free$");
        Matcher matcherFree = patternFree.matcher(status);

        Pattern patternOccupied = Pattern.compile("^interview with .*");
        Matcher matcherOccupied = patternOccupied.matcher(status);

        checkArgument((matcherFree.matches() || matcherOccupied.matches()), message);

        InterviewerState state;
        if (matcherFree.matches()) {
            state = InterviewerState.FREE;
        } else {
            return status;
        }
        return state.toString();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewerStatus)) {
            return false;
        }

        InterviewerStatus otherInterviewerStatus = (InterviewerStatus) other;
        return value.equals(otherInterviewerStatus.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
