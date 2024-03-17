package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.booking.Booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {

    public static final Booking CS1231_CONSULT = new BookingBuilder()
            .withDescription("CS1231 Consult")
            .withStartTime("2022-11-31 11:00")
            .withEndTime("2022-11-31 12:00")
            .build();

    public static final Booking CAREER_ADVISORY = new BookingBuilder()
            .withDescription("Career advisory consult")
            .withStartTime("2024-1-31 11:00")
            .withEndTime("2024-1-31 12:00")
            .build();

    public static final Booking CS2103T_CONSULT = new BookingBuilder()
            .withDescription("CS2103T consult")
            .withStartTime("2024-3-31 14:00")
            .withEndTime("2024-3-31 16:00")
            .build();

    public static final Booking CS2101_CONSULT = new BookingBuilder()
            .withDescription("CS2101 consult")
            .withStartTime("2024-3-24 14:00")
            .withEndTime("2024-3-24 16:00")
            .build();

    public static final Booking CS2109S_CONSULT = new BookingBuilder()
            .withDescription("CS2109 consult")
            .withStartTime("2024-2-22 11:00")
            .withEndTime("2024-2-22 13:00")
            .build();

    public static final Booking GENERIC_BOOKING = new BookingBuilder()
            .build();

    // Keywords
    public static final String KEYWORD_MATCHING_BOOK = "Book";

    /**
     * Returns an {@code AddressBook} with all the typical booking.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Booking booking : getTypicalBooking()) {
            ab.addBooking(booking);
        }
        return ab;
    }

    public static List<Booking> getTypicalBooking() {
        return new ArrayList<>(Arrays.asList(
                CS1231_CONSULT, CS2101_CONSULT, CS2103T_CONSULT, CS2109S_CONSULT,
                GENERIC_BOOKING));
    }
}
