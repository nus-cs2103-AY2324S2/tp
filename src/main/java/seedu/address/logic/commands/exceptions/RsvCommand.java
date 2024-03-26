package seedu.address.logic.commands.exceptions;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.reservation.Reservation;

/**
 * Adds a reservation for a person in the address book.
 */
public class RsvCommand extends Command {
    public static final String COMMAND_WORD = "rsv";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reservation for a person in the "
            + "address book.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + PREFIX_PAX + "PAX \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2024-04-17 "
            + PREFIX_TIME + "1800 "
            + PREFIX_PAX + "8 ";

    public static final String MESSAGE_SUCCESS = "New reservation added: %1$s";
    public static final String MESSAGE_DUPLICATE_RESERVATION = "This person has already made a reservation "
            + "at this date and timing in the address book";
    private final Reservation toAdd;

    /**
     * Creates an RsvCommand to add the specified {@code Reservation}
     */
    public RsvCommand(Reservation reservation) {
        requireNonNull(reservation);
        this.toAdd = reservation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasReservation(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESERVATION);
        }

        model.addReservation(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RsvCommand)) {
            return false;
        }

        RsvCommand otherRsvCommand = (RsvCommand) other;
        return toAdd.equals(otherRsvCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
