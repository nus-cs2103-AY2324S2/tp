package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;


/**
 * Wraps all data at the event-book level.
 * Duplicates are not allowed (by .isSameEvent comparison).
 */
public class EventBook implements ReadOnlyEventBook {

    private final UniqueEventList events;
    private final UniquePersonList personsOfSelectedEvent;
    private Event selectedEvent;

    // Non-static initialization block
    {
        events = new UniqueEventList();
        personsOfSelectedEvent = new UniquePersonList();
    }

    /**
     * Creates an EventBook
     */
    public EventBook() {
        Event event = new Event(new EventName("test"));
        Person alice = new Person(new Name("ALice"), new Phone("95352563"), new Email("heinz@example.com"), new Address("wall street"), new HashSet<>());
        Person ben = new Person(new Name("Ben"), new Phone("15312563"), new Email("heinz@example.com"), new Address("wall street"), new HashSet<>());
        Person ben2 = new Person(new Name("Be2n"), new Phone("15312563"), new Email("heinz@example.com"), new Address("wall street"), new HashSet<>());
        event.addPerson(alice);
        event.addPerson(ben);
        events.add(event);

        Event event2 = new Event(new EventName("test2"));
        event2.addPerson(ben2);
        event2.addPerson(ben);
        event2.addPerson(alice);
        events.add(event2);
    }

    /**
     * Creates an EventBook using the Events in the {@code toBeCopied}.
     */
    public EventBook(ReadOnlyEventBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // List overwrite operations

    /**
     * Replaces the contents of the event list with {@code events}.
     * {@code events} must not contain duplicate events.
     */
    public void setEvents(List<Event> events) {
        this.events.setEvents(events);
    }

    /**
     * Resets the existing data of this {@code EventBook} with {@code newData}.
     */
    public void resetData(ReadOnlyEventBook newData) {
        requireNonNull(newData);
        setEvents(newData.getEventList());
    }

    // Event-level operations

    /**
     * Returns true if an event with the same identity as {@code event} exists in the event book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return events.contains(event);
    }

    /**
     * Adds an event to the event book.
     * The event must not already exist in the event book.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the event book.
     * The event identity of {@code editedEvent} must not be the same as another existing event in the event book.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        events.setEvent(target, editedEvent);
    }

    /**
     * Removes {@code key} from this {@code EventBook}.
     * {@code key} must exist in the event book.
     */
    public void removeEvent(Event key) {
        events.remove(key);
    }

    // Select Event Methods

    /**
     * Selects the given event {@code event} from this {@code EventBook}
     * @param event must exist in the event book
     */
    public void selectEvent(Event event) {
        selectedEvent = event;
        personsOfSelectedEvent.setPersons(event.getPersonList());
    }

    // Util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("events", events)
                .toString();
    }

    @Override
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonsOfSelectedEventList() {
        return personsOfSelectedEvent.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventBook)) {
            return false;
        }

        EventBook otherEventBook = (EventBook) other;
        return events.equals(otherEventBook.events);
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
}
