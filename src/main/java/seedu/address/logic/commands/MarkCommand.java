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
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of students in the TA Toolkit "
            + "by their index in the displayed list for the specified week. Only absentees need to be specified, "
            + "the rest are assumed to be present.\n"
            + "Parameters: "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_INDICES + "INDEX [MORE_INDICES]...\n"
            + "Example: " + COMMAND_WORD + " w/1 i/1, 2, 3";

    public static final String MESSAGE_SUCCESS = "Marked the following students as absent for %1$s: %2$s";

    private static final Logger logger = LogsCenter.getLogger(MarkCommand.class);
    private final Week week;
    private final List<Index> absentees;

    /**
     * Creates an MarkCommand to add the specified {@code Person}
     */
    public MarkCommand(Week week, List<Index> absentees) {
        requireNonNull(week);
        requireNonNull(absentees);
        this.week = week;
        this.absentees = absentees;
        logger.info("MarkCommand created for " + week + " with absentees: " + absentees);
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

        String absenteeNames = absentStudents.stream()
                .map(Person::getName)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return new CommandResult(String.format(MESSAGE_SUCCESS, week, absenteeNames));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return week.equals(otherMarkCommand.week) && absentees.equals(otherMarkCommand.absentees);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("week", week)
                .add("absentees", absentees)
                .toString();
    }
}
