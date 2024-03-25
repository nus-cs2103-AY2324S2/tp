package seedu.address.testutil;

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    private TypicalEvents() {
    } // prevents instantiation

    /**
     * Returns the getBingoEvent() event.
     */
    public static Event getBingoEvent() {
        return new EventBuilder().withEventName("bingo").build();
    }

    /**
     * Returns the getHikingEvent() event.
     */
    public static Event getHikingEvent() {
        return new EventBuilder().withEventName("hiking").build();
    }

    /**
     * Returns the CONCERT event.
     */
    public static Event getConcertEvent() {
        return new EventBuilder().withEventName("concert").build();
    }

    /**
     * Returns the PARTY event.
     */
    public static Event getPartyEvent() {
        return new EventBuilder().withEventName("party").build();
    }

    /**
     * Returns the CONFERENCE event.
     */
    public static Event getConferenceEvent() {
        return new EventBuilder().withEventName("conference").build();
    }

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eventBook = new EventBook();
        for (Event event : getTypicalEvents()) {
            eventBook.addEvent(event);
        }
        return eventBook;
    }

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static Event[] getTypicalEvents() {
        Event bingo = getBingoEvent();
        Event hiking = getHikingEvent();
        Event concert = getConcertEvent();
        Event party = getPartyEvent();
        Event conference = getConferenceEvent();
        return new Event[]{bingo, hiking, concert, party, conference};
    }
}
