package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "Sample Event";

    private EventName eventName;


    /**
     * Creates an {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getEventName();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    public Event build() {
        return new Event(eventName);
    }
}
