package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.person.Person;

/**
 * Manages a list of meetings, ensuring each meeting is unique and nulls are not permitted.
 * Uniqueness of a meeting is determined by the {@code Meeting#isSameMeeting(Meeting)} method. This ensures
 * that when adding or updating meetings, each meeting is distinct based on its fields within the UniqueMeetingList.
 * In contrast, the removal of meetings relies on the {@code Meeting#equals(Object)} method to ensure
 * that a meeting is removed only if it matches exactly as a meeting object.
 *
 * This approach allows for nuanced control over meeting management:
 * - When adding or updating, meetings are considered the same based on specific attributes,
 * allowing for flexible uniqueness criteria.
 * - When removing, the stricter {@code equals} method ensures that only an exact match is removed,
 * preventing unintended deletions.
 *
 * Supports a minimal set of list operations.
 *
 * @see Meeting#isSameMeeting(Meeting)
 */
public class UniqueMeetingList implements Iterable<Meeting> {

    private final ObservableList<Meeting> internalList = FXCollections.observableArrayList();
    private final ObservableList<Meeting> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Meeting as the given argument.
     */
    public boolean contains(Meeting meeting) {
        requireNonNull(meeting);
        return internalList.stream().anyMatch(meeting::isSameMeeting);
    }

    /**
     * Adds a Meeting to the list.
     * The Meeting must not already exist in the list.
     */
    public void add(Meeting toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMeetingException();
        }
        internalList.add(toAdd);
        internalList.sort(Comparator.comparing(Meeting::getDateTime));
    }

    /**
     * Deletes a Meeting from the list.
     */
    public void delete(Meeting meeting) {
        requireAllNonNull(meeting);
        internalList.remove(meeting);
    }

    /**
     * Replaces the Meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the list.
     * The Meeting of {@code editedMeeting} must not be the same as another existing
     * Meeting in the list.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MeetingNotFoundException();
        }

        if (!target.isSameMeeting(editedMeeting) && contains(editedMeeting)) {
            throw new DuplicateMeetingException();
        }

        internalList.set(index, editedMeeting);
        internalList.sort(Comparator.comparing(Meeting::getDateTime));
    }

    /**
     * Removes the equivalent Meeting from the list.
     * The Meeting must exist in the list.
     */
    public void remove(Meeting toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MeetingNotFoundException();
        }
    }

    /**
     * Removes the Meetings which belong to Person p.
     * @see Meeting#belongsTo(Person)
     */
    public void removePerson(Person p) {
        requireNonNull(p);
        List<Meeting> toRemove =
                internalList.stream().filter((m) -> m.belongsTo(p)).collect(Collectors.toList());
        toRemove.forEach(this::remove);
    }

    public void setMeetings(UniqueMeetingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        internalList.sort(Comparator.comparing(Meeting::getDateTime));
    }

    /**
     * Replaces the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate Meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.setAll(meetings);
        internalList.sort(Comparator.comparing(Meeting::getDateTime));
    }

    /**
     * Replaces the Person Meetings with new Meetings with newly edited Person upon editing said Person.
     *
     * @param oldMeetings old Meetings of the Person
     * @param toReplace new Meetings of the Person
     */
    public void editPersonMeetings(ArrayList<Meeting> oldMeetings, ArrayList<Meeting> toReplace) {
        requireAllNonNull(oldMeetings, toReplace);

        // Create a copy of the internalList
        List<Meeting> meetingsList = new ArrayList<>(internalList);

        // Iterate through oldMeetings and replace with toReplace at the same index
        for (int i = 0; i < meetingsList.size(); i++) {
            if (oldMeetings.contains(meetingsList.get(i))) {
                int replaceIndex = oldMeetings.indexOf(meetingsList.get(i));
                if (replaceIndex < toReplace.size()) {
                    // Replace with the corresponding meeting from toReplace
                    meetingsList.set(i, toReplace.get(replaceIndex));
                }
            }
        }

        // Clear the internalList and add all elements from the updated meetingsList
        internalList.clear();
        internalList.addAll(meetingsList);
        internalList.sort(Comparator.comparing(Meeting::getDateTime));
    }

    /**
     * Adds to the contents of this list with {@code meetings}.
     * {@code meetings} must not contain duplicate Meetings.
     */
    public void addAll(List<Meeting> meetings) {
        requireAllNonNull(meetings);
        if (!meetingsAreUnique(meetings)) {
            throw new DuplicateMeetingException();
        }

        internalList.addAll(meetings);
        internalList.sort(Comparator.comparing(Meeting::getDateTime));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Meeting> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Meeting> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueMeetingList)) {
            return false;
        }

        UniqueMeetingList otherUniqueMeetingList = (UniqueMeetingList) other;
        return internalList.equals(otherUniqueMeetingList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code meetings} contains only unique Meetings.
     */
    private boolean meetingsAreUnique(List<Meeting> meetings) {
        for (int i = 0; i < meetings.size() - 1; i++) {
            for (int j = i + 1; j < meetings.size(); j++) {
                if (meetings.get(i).isSameMeeting(meetings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
