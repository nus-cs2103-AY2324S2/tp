package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns a list of meetings.
     *
     * @return List of meetings.
     */
    ObservableList<Meeting> getMeetingList();

    /**
     * Returns true if the meeting list contains an equivalent meeting as the given argument.
     *
     * @param meeting Meeting to check.
     * @return True if the meeting list contains an equivalent meeting as the given argument.
     */
    boolean hasMeeting(Meeting meeting);

}
