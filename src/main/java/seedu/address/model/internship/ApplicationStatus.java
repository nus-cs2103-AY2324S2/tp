package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's application status in the internship book.
 */
public class ApplicationStatus {
    public static final String MESSAGE_CONSTRAINTS = "Application statuses have to be either to_apply, pending, "
            + "rejected, accepted, or ongoing";

    public static final String VALIDATION_REGEX = "(?i)to_apply|pending|rejected|accepted|ongoing";

    /**
     * Enum of statuses
     */
    private enum StatusEnum {
        TO_APPLY,
        PENDING,
        REJECTED,
        ACCEPTED,
        ONGOING

    }

    public final StatusEnum applicationStatus;

    /**
     * Constructs a {@code ApplicationStatus}.
     *
     * @param applicationStatus A valid application status.
     */
    public ApplicationStatus(String applicationStatus){
        requireNonNull(applicationStatus);
        checkArgument(isValidApplicationStatus(applicationStatus), MESSAGE_CONSTRAINTS);
        this.applicationStatus = StatusEnum.valueOf(applicationStatus.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid ApplicationStatus.
     */
    public static boolean isValidApplicationStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return applicationStatus.toString();
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }

        ApplicationStatus otherApplicationStatus = (ApplicationStatus) other;
        return applicationStatus.equals(otherApplicationStatus.applicationStatus);
    }

    @Override
    public int hashCode() {
        return applicationStatus.hashCode();
    }
}
