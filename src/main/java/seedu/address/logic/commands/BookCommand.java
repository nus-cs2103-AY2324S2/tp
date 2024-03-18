package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Lists all persons in the address book to the user.
 */
public class BookCommand extends Command {

    public static final String COMMAND_WORD = "book";

    public static final String MESSAGE_SUCCESS = "Booking created!";
    public static final String MESSAGE_DUPLICATE_BOOKING = "Error: This booking already exists.";

    public static final String MESSAGE_USAGE = BookCommand.COMMAND_WORD + ": Adds a booking to the system.\n"
            + "Parameters: "
            + PREFIX_NAME + "{description} "
            + PREFIX_START_TIME + "{start_time} "
            + PREFIX_END_TIME + "{end_time}\n"
            + "Example usage: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John's Birthday Party "
            + PREFIX_START_TIME + "2023-12-31 19:00 "
            + PREFIX_END_TIME + "2023-12-31 23:00";
    private final Booking toAdd;

    /**
     * Creates a BookCommand to add {@code booking}
     * @param booking
     */
    public BookCommand(Booking booking) {
        requireNonNull(booking);
        this.toAdd = booking;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBooking(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatBooking(toAdd)));
    }
}
