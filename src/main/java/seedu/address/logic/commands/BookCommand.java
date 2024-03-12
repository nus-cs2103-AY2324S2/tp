package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Lists all persons in the address book to the user.
 */
public class BookCommand extends Command {

    public static final String COMMAND_WORD = "book";

    public static final String MESSAGE_SUCCESS = "Booking created!";

    public static final String MESSAGE_USAGE = BookCommand.COMMAND_WORD + ": Creates a booking with a person. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_START_TIME + "START TIME "
            + PREFIX_END_TIME + "END TIME";

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
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.addBooking(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatBooking(toAdd)));
    }
}
