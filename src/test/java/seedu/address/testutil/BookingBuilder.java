package seedu.address.testutil;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Description;
import seedu.address.model.booking.EndTime;
import seedu.address.model.booking.StartTime;

public class BookingBuilder {

    public static final String DEFAULT_DESCRIPTION = "Default booking description";
    public static final String DEFAULT_START_TIME = "2023-12-31 19:00";
    public static final String DEFAULT_END_TIME = "2023-12-31 21:00";

    private Description description;
    private StartTime start;
    private EndTime end;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        start = new StartTime(DEFAULT_START_TIME);
        end = new EndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        description = bookingToCopy.getDescription();
        start = bookingToCopy.getStart();
        end = bookingToCopy.getEnd();
    }

    /**
     * Sets the {@code Description} of the {@code Booking} that we are building.
     */
    public BookingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code start} of the {@code Booking} that we are building.
     */
    public BookingBuilder withStartTime(String start) {
        this.start = new StartTime(start);
        return this;
    }

    /**
     * Sets the {@code end} of the {@code Booking} that we are building.
     */
    public BookingBuilder withEndTime(String end) {
        this.end = new EndTime(end);
        return this;
    }

    /**
     * Builds the {@code Booking} with the relevant information.
     */
    public Booking build() {
        return new Booking(description, start, end);
    }
}
