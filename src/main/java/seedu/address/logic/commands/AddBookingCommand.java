package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

public class AddBookingCommand extends Command {
    public static final String COMMAND_WORD = "addBooking";
    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    private final Booking toAdd;

    public AddBookingCommand(Booking booking) {
        this.toAdd = booking;
    }

    @Override
    public CommandResult execute(Model model) {
        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}