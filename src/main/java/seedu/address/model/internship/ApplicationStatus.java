package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Internship's application status in the internship book.
 */
public class ApplicationStatus {
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
     * @param status A valid applicationStatus.
     */
    public ApplicationStatus(String status) {
        if (status == null) {
            this.applicationStatus = StatusEnum.TO_APPLY;
            return;
        }
        switch (status) {
        case "to apply":
            this.applicationStatus = StatusEnum.TO_APPLY;
            break;
        case "pending":
            this.applicationStatus = StatusEnum.PENDING;
            break;
        case "rejected":
            this.applicationStatus = StatusEnum.REJECTED;
            break;
        case "accepted":
            this.applicationStatus = StatusEnum.ACCEPTED;
            break;
        case "ongoing":
            this.applicationStatus = StatusEnum.ONGOING;
            break;
        default:
            throw new IllegalArgumentException("Invalid application status: " + status);
        }
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
