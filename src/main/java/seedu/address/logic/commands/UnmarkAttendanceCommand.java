package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.util.CommandMessageUsageUtil.generateMessageUsage;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_NUSNET;
import static seedu.address.logic.commands.util.ParameterSyntax.PARAMETER_WEEK;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNet;
import seedu.address.model.person.Person;
import seedu.address.model.weeknumber.WeekNumber;

/**
 *
 */
public class UnmarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = generateMessageUsage(
            COMMAND_WORD,
            "Unmarks the attendance of the person identified by their NusNet by removing the specified week to "
                    + "their attendance set. ",
            PARAMETER_NUSNET, PARAMETER_WEEK);

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

        if (!updatedWeekAttendance.remove(weekNumber)) {
            String formattedMessage = String.format("%1$s%2$s, %3$s, Week %4$s",
                    Messages.MESSAGE_UNMARK_NONEXISITING_ATTENDANCE_SUCCESS,
                    personToUnmark.getName(), personToUnmark.getNusNet(), weekNumber);

            return new CommandResult(formattedMessage);
        }

        Person updatedPerson = new Person(personToUnmark.getName(), personToUnmark.getPhone(),
                personToUnmark.getEmail(), personToUnmark.getNusNet(), personToUnmark.getAddress(),
                updatedWeekAttendance, personToUnmark.getTags());

        model.setPerson(personToUnmark, updatedPerson);

        String formattedMessage = String.format("%1$s%2$s, %3$s, Week %4$s",
                Messages.MESSAGE_UNMARKED_ATTENDANCE_SUCCESS,
                updatedPerson.getName(), updatedPerson.getNusNet(), weekNumber);

        return new CommandResult(formattedMessage);

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
