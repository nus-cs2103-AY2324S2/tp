package seedu.address.model;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an event book
 */
public interface ReadOnlyEventBook {

    /**
     * Returns an unmodifiable view of the events list.
     * This list will not contain any duplicate events.
     */
    ObservableList<Event> getEventList();

    /**
     * Returns an unmodifiable view of the persons list of the selected event.
     * This list will not contain any duplicate events.
     * This list is empty is no event is currently selected.
     */
    ObservableList<Person> getPersonsOfSelectedEventList();
}
