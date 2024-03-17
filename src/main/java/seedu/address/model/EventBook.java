package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.UniqueEventList;
import seedu.address.model.person.Person;
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
     * Checks if an event is currently selected.
     *
     * @return true if an event is selected, false otherwise
     */
    public boolean isAnEventSelected() {
        return selectedEvent != null;
    }

    /**
     * Selects the given event {@code event} from this {@code EventBook}
     * @param event must exist in the event book
     */
    public void selectEvent(Event event) {
        selectedEvent = event;
        personsOfSelectedEvent.setPersons(event.getPersonList());
    }

    /**
     * Deselects event.
     */
    public void deselectEvent() {
        selectedEvent = null;
        personsOfSelectedEvent.setPersons(new ArrayList<>());
    }

    /**
     * Checks if a person is part of the selected event.
     *
     * @param person The person to check.
     * @return {@code true} if the person is part of the selected event, {@code false} otherwise.
     */
    public boolean isPersonInSelectedEvent(Person person) {
        if (!isAnEventSelected()) {
            return false;
        }
        return selectedEvent.hasPerson(person);
    }

    /**
     * Adds a person to the selected event if an event is currently selected.
     * If no event is selected, the person will not be added.
     *
     * @param person The person to be added to the selected event.
     */
    public void addPersonToSelectedEvent(Person person) {
        if (isAnEventSelected()) {
            selectedEvent.addPerson(person);
        }
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
