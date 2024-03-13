package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.weeknumber.WeekNumber;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

public class UnmarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance of the person identified by their NusNetID by removing the specified week to " +
            "their attendance set. "
            + "Parameters: "
            + PREFIX_NUSNET + "NUSNET "
            + PREFIX_WEEK + "WEEK\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NUSNET + "e0123456 " + PREFIX_WEEK + " 1";

    public static final String MESSAGE_UNMARKED_ATTENDANCE_SUCCESS = "Unmarked Attendance: %1$s";

    private final NusNet nusNet;
    private final WeekNumber weekNumber;

    /**
     * @param nusNet of the person to mark attendance for
     * @param weekNumber the week number to mark attendance for
     */
    public UnmarkAttendanceCommand(NusNet nusNet, WeekNumber weekNumber) {
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
        Person personToUnmark = optionalPersonToMark.get();

        Set<WeekNumber> updatedWeekAttendance = new HashSet<>(personToUnmark.getAttendance());

        Person updatedPerson = new Person(personToUnmark.getName(), personToUnmark.getPhone(), personToUnmark.getEmail(),
                personToUnmark.getNusNet(), personToUnmark.getAddress(), updatedWeekAttendance, personToUnmark.getTags());
        model.setPerson(personToUnmark, updatedPerson);
        return new CommandResult(String.format(MESSAGE_UNMARKED_ATTENDANCE_SUCCESS, updatedPerson));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkAttendanceCommand)) {
            return false;
        }

        UnmarkAttendanceCommand otherUnmarkCommand = (UnmarkAttendanceCommand) other;
        return nusNet.equals(otherUnmarkCommand.nusNet) && weekNumber.equals(otherUnmarkCommand.weekNumber);
    }
}
