package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDICES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Week;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of students in the TA Toolkit "
            + "by their index in the displayed list for the specified week. Only absentees need to be specified, "
            + "the rest are assumed to be present.\n"
            + "Parameters: "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_INDICES + "INDEX [MORE_INDICES]...\n"
            + "Example: " + COMMAND_WORD + " w/1 i/1, 2, 3";

    public static final String MESSAGE_SUCCESS = "Attendance successfully marked for %1$s";

    private static final Logger logger = LogsCenter.getLogger(AttendanceCommand.class);
    private final Week week;
    private final List<Index> absentees;

    /**
     * Creates an AttendanceCommand to add the specified {@code Person}
     */
    public AttendanceCommand(Week week, List<Index> absentees) {
        requireNonNull(week);
        requireNonNull(absentees);
        this.week = week;
        this.absentees = absentees;
        logger.info("AttendanceCommand created for " + week + " with absentees: " + absentees);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // We don't proceed even if the other indices are valid since it's likely that the user
        // has made a mistake in the other inputs as well.
        for (Index index : absentees) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        List<Person> absentStudents = this.absentees.stream()
                .map(index -> lastShownList.get(index.getZeroBased()))
                .collect(Collectors.toList());

        for (Person student : absentStudents) {
            student.markAbsent(week);
        }

        model.getLastViewedPerson().ifPresent(model::updateLastViewedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, week));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        AttendanceCommand otherAttendanceCommand = (AttendanceCommand) other;
        return week.equals(otherAttendanceCommand.week) && absentees.equals(otherAttendanceCommand.absentees);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("week", week)
                .add("absentees", absentees)
                .toString();
    }
}
