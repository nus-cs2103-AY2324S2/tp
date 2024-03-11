package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Adds a booking to the booking system.
 */
public class AddBookingCommand extends Command {
    public static final String COMMAND_WORD = "addBooking";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the booking system. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_START_TIME + "START "
            + PREFIX_END_TIME + "END\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John's Birthday Party "
            + PREFIX_START_TIME + "2023-12-31T19:00 "
            + PREFIX_END_TIME + "2023-12-31T23:00";

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking conflicts with an existing booking in the system";

    private final Booking toAdd;

    /**
     * Creates an AddBookingCommand to add the specified {@code Booking}
     */
    public AddBookingCommand(Booking booking) {
        requireNonNull(booking);
        toAdd = booking;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // This is a placeholder for conflict checking logic. You'll need to implement
        // model.hasBookingConflict(toAdd) or similar method based on your model's capabilities.
        if (model.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AddBookingCommand)) {
            return false;
        }

        AddBookingCommand otherAddBookingCommand = (AddBookingCommand) other;
        return toAdd.equals(otherAddBookingCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
