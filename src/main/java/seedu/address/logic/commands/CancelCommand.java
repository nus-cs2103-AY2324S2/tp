package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Deletes a booking identified using it's displayed index from the address book.
 */

public class CancelCommand extends Command {

    public static final String COMMAND_WORD = "cancel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by the index number used in the displayed booking list.\n"
            + "Parameters: {index} (must be a positive integer)\n"
            + "Example usage: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CANCEL_BOOKING_SUCCESS = "Cancelled Booking: %1$s";

    private final Index targetIndex;

    public CancelCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.cancelBooking(bookingToDelete);
        return new CommandResult(String.format(MESSAGE_CANCEL_BOOKING_SUCCESS,
                Messages.formatCancel(bookingToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CancelCommand)) {
            return false;
        }

        CancelCommand otherCancelCommand = (CancelCommand) other;
        return targetIndex.equals(otherCancelCommand.targetIndex);
    }
}
