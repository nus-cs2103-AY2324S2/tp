package seedu.address.model.meeting;

import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Meeting} belongs to the specified client.
 */
public class MeetingBelongingToClientPredicate implements Predicate<Meeting> {
    private final Person client;

    public MeetingBelongingToClientPredicate(Person client) {
        this.client = client;
    }

    @Override
    public boolean test(Meeting otherMeeting) {
        return this.client.equals(otherMeeting.getClient());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingBelongingToClientPredicate)) {
            return false;
        }

        MeetingBelongingToClientPredicate meetingBelongingToClientPredicate =
                (MeetingBelongingToClientPredicate) other;
        return this.client.equals(meetingBelongingToClientPredicate.client);
    }

    @Override
    public String toString() {
        return this.client.toString();
    }
}

