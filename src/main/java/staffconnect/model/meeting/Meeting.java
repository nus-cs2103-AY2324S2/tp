package staffconnect.model.meeting;


import static staffconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Meeting event in the staff book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    private final Description description;
    private final MeetDateTime startDate;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param description A valid meeting description.
     * @param startDate   A valid time and date for the meeting.
     */

    public Meeting(Description description, MeetDateTime startDate) {
        requireAllNonNull(description, startDate);
        this.description = description;
        this.startDate = startDate;
    }

    public Description getDescription() {
        return description;
    }

    public MeetDateTime getStartDate() {
        return startDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, startDate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return description.equals(otherMeeting.description) && startDate.equals(otherMeeting.startDate);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return startDate + ":" + description;
    }



}
