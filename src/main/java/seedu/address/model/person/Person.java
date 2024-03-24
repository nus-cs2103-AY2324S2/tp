package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    private final Policy policy;

    private final Relationship relationship;

    private final Set<Tag> tags = new HashSet<>();


    private List<Meeting> meetings;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Relationship relationship,
                  Policy policy, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, relationship, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.policy = policy;
        this.relationship = relationship;
        this.tags.addAll(tags);
        this.meetings = new ArrayList<>();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Policy getPolicy() {
        return policy;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public boolean isClient() {
        return relationship.value.equals("client");
    }

    public boolean isPartner() {
        return relationship.value.equals("partner");
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }



    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && relationship.equals(otherPerson.relationship)
                && policy.equals(otherPerson.policy)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, policy, relationship, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("relationship", relationship)
                .add("policy", policy)
                .add("tags", tags)
                .toString();
    }





    //Meetings composition methods


    public List<Meeting> getMeetings() {
        return this.meetings;
    }

    /**
     * Adds a meeting to the list of meetings associated with this person.
     *
     * @param meeting The meeting to be added.
     * @throws IllegalArgumentException if the meeting overlaps with existing meetings or
     *     if scheduling constraints are violated.
     */
    public void addMeeting(Meeting meeting) {
        LocalDate today = LocalDate.now();
        LocalDate meetingDate = meeting.getMeetingDate();

        if (meetings.size() >= 5) {
            throw new IllegalArgumentException("Cannot have more than 5 meetings.");
        } else if (meetingDate.isBefore(today)) {
            throw new IllegalArgumentException("Cannot schedule a meeting in the past.");
        } else if (meetingDate.isAfter(today.plusYears(1))) { // Assuming 1 year is too far in the future
            throw new IllegalArgumentException("Cannot schedule a meeting more than a year in the future.");
        } else if (isOverlapWithOtherMeetings(meeting)) {
            throw new IllegalArgumentException("Meeting overlaps with existing meetings with this client.");
        } else {
            meetings.add(meeting);
        }
    }

    /**
     * Sets the list of meetings associated with this person.
     *
     * @param meetings The list of meetings to be set.
     */
    public void setMeetings(List<Meeting> meetings) {

        this.meetings = meetings;

    }

    /**
     * Reschedules a meeting associated with this person.
     *
     * @param index      The index of the meeting to be rescheduled.
     * @param newDateTime The new date and time for the meeting.
     * @throws IllegalArgumentException if the rescheduled meeting overlaps with existing meetings.
     */
    public void rescheduleMeeting(int index, LocalDateTime newDateTime) {
        Meeting meetingToReschedule = meetings.get(index);
        Meeting rescheduledMeeting = new Meeting(
                newDateTime.toLocalDate(),
                newDateTime.toLocalTime(),
                meetingToReschedule.getDuration(),
                meetingToReschedule.getAgenda(),
                meetingToReschedule.getNotes()
        );

        // Remove the old meeting and try adding the rescheduled one
        meetings.remove(index);
        if (!isOverlapWithOtherMeetings(rescheduledMeeting)) {
            meetings.add(rescheduledMeeting);
        } else {
            // If there's an overlap, add the old meeting back and throw an exception
            meetings.add(index, meetingToReschedule);
            throw new IllegalArgumentException("Rescheduled meeting overlaps with existing meetings.");
        }
    }


    public void cancelMeeting(int index) {
        meetings.remove(index);
    }

    private boolean isOverlapWithOtherMeetings(Meeting meetingToCheck) {



        LocalDateTime startDateTimeToCheck = LocalDateTime.of(meetingToCheck.getMeetingDate(),
                meetingToCheck.getMeetingTime());
        LocalDateTime endDateTimeToCheck = startDateTimeToCheck.plus(meetingToCheck.getDuration());

        for (Meeting meeting : meetings) {
            LocalDateTime startDateTime = LocalDateTime.of(meeting.getMeetingDate(), meeting.getMeetingTime());
            LocalDateTime endDateTime = startDateTime.plus(meeting.getDuration());

            if (startDateTimeToCheck.isBefore(endDateTime) && endDateTimeToCheck.isAfter(startDateTime)) {
                return true;
            }
        }
        return false;
    }

    public Person getCopy() {
        Person p = new Person(this.name, this.phone, this.email, this.address, this.relationship,
                this.getPolicy(), this.getTags());

        // Create a deep copy of the meetings
        List<Meeting> copiedMeetings = new ArrayList<>();
        for (Meeting meeting : this.getMeetings()) {
            Meeting copiedMeeting = new Meeting(
                    meeting.getMeetingDate(),
                    meeting.getMeetingTime(),
                    meeting.getDuration(),
                    meeting.getAgenda(),
                    meeting.getNotes()
            );
            copiedMeetings.add(copiedMeeting);
        }
        p.setMeetings(copiedMeetings);

        return p;
    }
}
