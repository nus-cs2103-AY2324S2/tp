package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Booking> bookings = model.getFilteredBookingList();
        AddressBook newAddressBook = new AddressBook();
        newAddressBook.setBookings(bookings);
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
