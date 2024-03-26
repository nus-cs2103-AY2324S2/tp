package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.reservation.Pax;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.RsvDate;
import seedu.address.model.reservation.RsvTime;

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

    private final Index index;
    private final RsvDate date;
    private final RsvTime time;
    private final Pax pax;

    /**
     * Creates an RsvCommand to add the specified {@code Reservation}
     */
    public RsvCommand(Index index, RsvDate date, RsvTime time, Pax pax) {
        requireNonNull(index);
        requireNonNull(date);
        requireNonNull(time);
        requireNonNull(pax);
        this.index = index;
        this.date = date;
        this.time = time;
        this.pax = pax;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personMakingRsv = lastShownList.get(index.getZeroBased());
        Reservation toAdd = new Reservation(personMakingRsv, this.date, this.time, this.pax);

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
        return index.equals(otherRsvCommand.index)
                && date.equals(otherRsvCommand.date)
                && time.equals(otherRsvCommand.time)
                && pax.equals(otherRsvCommand.pax);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("date", date)
                .add("time", time)
                .add("pax", pax)
                .toString();
    }
}
