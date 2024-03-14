package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.weeknumber.WeekNumber;

/**
 * Marks attendance of an existing person in the address book.
 */
public class MarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the person identified by their NusNet "
            + "by adding the specified week to their attendance set. "
            + "Parameters: "
            + PREFIX_NUSNET + "NUSNET "
            + PREFIX_WEEK + "WEEK\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NUSNET + "e0123456 " + PREFIX_WEEK + " 1";

    private final NusNet nusNet;
    private final WeekNumber weekNumber;

    /**
     * @param nusNet of the person to mark attendance for
     * @param weekNumber the week number to mark attendance for
     */
    public MarkAttendanceCommand(NusNet nusNet, WeekNumber weekNumber) {
        requireNonNull(nusNet);
        requireNonNull(weekNumber);
        this.nusNet = nusNet;
        this.weekNumber = weekNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Person> optionalPersonToMark = model.getPersonByNusNet(nusNet);

        if (!optionalPersonToMark.isPresent()) {
            throw new CommandException(Messages.MESSAGE_MISSING_NUSNET);
        }
        Person personToMark = optionalPersonToMark.get();

        Set<WeekNumber> updatedWeekAttendance = new HashSet<>(personToMark.getAttendance());

        if (!updatedWeekAttendance.add(weekNumber)) {
            String formattedMessage = String.format("%1$s%2$s, %3$s, Week %4$s",
                    Messages.MESSAGE_MARK_EXISTING_ATTENDANCE_SUCCESS,
                    personToMark.getName(), personToMark.getNusNet(), weekNumber);

            return new CommandResult(formattedMessage);
        }

        Person updatedPerson = new Person(personToMark.getName(), personToMark.getPhone(), personToMark.getEmail(),
                personToMark.getNusNet(), personToMark.getAddress(), updatedWeekAttendance, personToMark.getTags());

        model.setPerson(personToMark, updatedPerson);

        String formattedMessage = String.format("%1$s%2$s, %3$s, Week %4$s", Messages.MESSAGE_MARKED_ATTENDANCE_SUCCESS,
                updatedPerson.getName(), updatedPerson.getNusNet(), weekNumber);

        return new CommandResult(formattedMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAttendanceCommand)) {
            return false;
        }

        MarkAttendanceCommand otherMarkAttendanceCommand = (MarkAttendanceCommand) other;
        return nusNet.equals(otherMarkAttendanceCommand.nusNet)
                && weekNumber.equals(otherMarkAttendanceCommand.weekNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nusNet", nusNet)
                .add("weekNumber", weekNumber)
                .toString();
    }
}
