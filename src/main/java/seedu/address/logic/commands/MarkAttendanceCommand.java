package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.weekNumber.WeekNumber;

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
            + PREFIX_WEEK + "WEEK";

    public static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Marked Attendance for Person: %1$s";
    public static final String MESSAGE_DUPLICATE_WEEK = "This week's attendance has already been marked for the person.";

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
        Person personToMark;

        if (optionalPersonToMark.isPresent()) {
            personToMark = optionalPersonToMark.get();
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_NUSNET_FOR_MARK);
        }

        Set<WeekNumber> updatedWeekAttendance = new HashSet<>(personToMark.getAttendance());

        if (!updatedWeekAttendance.add(weekNumber)) {
            throw new CommandException(MESSAGE_DUPLICATE_WEEK);
        }

        Person updatedPerson = new Person(personToMark.getName(), personToMark.getPhone(), personToMark.getEmail()
                ,personToMark.getNusNet(), personToMark.getAddress(), updatedWeekAttendance, personToMark.getTags());

        model.setPerson(personToMark, updatedPerson);
        return new CommandResult(String.format(MESSAGE_MARK_ATTENDANCE_SUCCESS, updatedPerson));
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
        return nusNet.equals(otherMarkAttendanceCommand.nusNet) &&
                weekNumber.equals(otherMarkAttendanceCommand.weekNumber);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nusNetToMark", nusNet)
                .add("week", weekNumber)
                .toString();
    }
}
