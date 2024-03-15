package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.BINGO;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;

public class EventBookTest {

    private final EventBook eventBook = new EventBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventBook.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEventBook_replacesData() {
        EventBook newData = getTypicalEventBook();
        eventBook.resetData(newData);
        assertEquals(newData, eventBook);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same identity fields
        Event editedBingo = new EventBuilder(BINGO)
                .build();
        List<Event> newEvents = Arrays.asList(BINGO, editedBingo);
        EventBookStub newData = new EventBookStub(newEvents);

        assertThrows(DuplicateEventException.class, () -> eventBook.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInEventBook_returnsFalse() {
        assertFalse(eventBook.hasEvent(BINGO));
    }

    @Test
    public void hasEvent_eventInEventBook_returnsTrue() {
        eventBook.addEvent(BINGO);
        assertTrue(eventBook.hasEvent(BINGO));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInEventBook_returnsTrue() {
        eventBook.addEvent(BINGO);
        Event editedBingo = new EventBuilder(BINGO)
                .build();
        assertTrue(eventBook.hasEvent(editedBingo));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eventBook.getEventList().remove(0));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        EventBook eventBook = new EventBook();
        assertTrue(eventBook.equals(eventBook));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        EventBook eventBook = new EventBook();
        Object differentObject = new Object();
        assertFalse(eventBook.equals(differentObject));
    }

    @Test
    public void toStringMethod() {
        String expected = EventBook.class.getCanonicalName() + "{events=" + eventBook.getEventList() + "}";
        assertEquals(expected, eventBook.toString());
    }

    /**
     * A stub ReadOnlyEventBook whose events list can violate interface constraints.
     */
    private static class EventBookStub implements ReadOnlyEventBook {
        private final ObservableList<Event> events = FXCollections.observableArrayList();
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        EventBookStub(Collection<Event> events) {
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }

        @Override
        public ObservableList<Person> getPersonsOfSelectedEventList() {
            return persons;
        }
    }
}
