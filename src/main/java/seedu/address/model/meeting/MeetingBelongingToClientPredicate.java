package seedu.address.model.meeting;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.function.Predicate;

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

