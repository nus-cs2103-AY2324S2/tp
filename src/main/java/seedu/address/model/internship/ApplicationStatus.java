package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Internship's application status in the internship book.
 */
public class ApplicationStatus {
    /**
     * Enum of statuses
     */
    public enum StatusEnum {
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
     * @param applicationStatus A valid applicationStatus.
     */
    public ApplicationStatus(StatusEnum applicationStatus) {
        requireNonNull(applicationStatus);
        this.applicationStatus = applicationStatus;
    }

    @Override
    public String toString() {
        switch (applicationStatus) {
        case TO_APPLY:
            return "To Apply";
        case PENDING:
            return "Pending";
        case REJECTED:
            return "Rejected";
        case ACCEPTED:
            return "Accepted";
        case ONGOING:
            return "Ongoing";
        default:
            throw new IllegalArgumentException("Unexpected application status: " + applicationStatus);
        }
    }


    @Override
    public int hashCode() {
        return applicationStatus.hashCode();
    }
}
