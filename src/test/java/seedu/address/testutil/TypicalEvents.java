package seedu.address.testutil;

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event BINGO = new EventBuilder().withEventName("bingo").build();
    public static final Event HIKING = new EventBuilder().withEventName("hiking").build();
    public static final Event CONCERT = new EventBuilder().withEventName("concert").build();
    public static final Event PARTY = new EventBuilder().withEventName("party").build();
    public static final Event CONFERENCE = new EventBuilder().withEventName("conference").build();

    private TypicalEvents() {
    } // prevents instantiation

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

    public static Event[] getTypicalEvents() {
        return new Event[]{BINGO, HIKING, CONCERT, PARTY, CONFERENCE};
    }
}
