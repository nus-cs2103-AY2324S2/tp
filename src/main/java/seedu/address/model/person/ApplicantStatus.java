package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.person.enums.ApplicantState;

/**
 * Represents an Applicant's status in the Tether.
 * Guarantee: is valid as declared in {@link #matchStatus(String, String)}
 */
public class ApplicantStatus extends Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status can only be one of \"resume review\", \"pending interview\", \"completed interview\"\n"
                    + "\"waiting list\", \"accepted\", or \"rejected\"";

    public final String value;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A status.
     */
    public ApplicantStatus(String status) {
        requireNonNull(status);
        value = matchStatus(status.toLowerCase(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Checks and returns the status if it is valid.
     */
    public String matchStatus(String status, String message) {
        Pattern patternResumeReview = Pattern.compile("^resume review$");
        Matcher matcherResumeReview = patternResumeReview.matcher(status);

        Pattern patternPendingIntv = Pattern.compile("^pending interview$");
        Matcher matcherPendingIntv = patternPendingIntv.matcher(status);

        Pattern patternCompletedIntv = Pattern.compile("^completed interview$");
        Matcher matcherCompletedIntv = patternCompletedIntv.matcher(status);

        Pattern patternWaitingList = Pattern.compile("^waiting list$");
        Matcher matcherWaitingList = patternWaitingList.matcher(status);

        Pattern patternAccepted = Pattern.compile("^accepted$");
        Matcher matcherAccepted = patternAccepted.matcher(status);

        Pattern patternRejected = Pattern.compile("^rejected$");
        Matcher matcherRejected = patternRejected.matcher(status.toLowerCase());

        checkArgument((matcherResumeReview.matches()
                        || matcherPendingIntv.matches()
                        || matcherCompletedIntv.matches()
                        || matcherWaitingList.matches()
                        || matcherAccepted.matches()
                        || matcherRejected.matches()), message);

        ApplicantState state;
        if (matcherResumeReview.matches()) {
            state = ApplicantState.STAGEONE;
        } else if (matcherPendingIntv.matches()) {
            state = ApplicantState.STAGETWO;
        } else if (matcherCompletedIntv.matches()) {
            state = ApplicantState.STAGETHREE;
        } else if (matcherWaitingList.matches()) {
            state = ApplicantState.OUTCOMEONE;
        } else if (matcherAccepted.matches()) {
            state = ApplicantState.OUTCOMETWO;
        } else {
            state = ApplicantState.OUTCOMETHREE;
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
        if (!(other instanceof ApplicantStatus)) {
            return false;
        }

        ApplicantStatus otherApplicantStatus = (ApplicantStatus) other;
        return value.equals(otherApplicantStatus.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
