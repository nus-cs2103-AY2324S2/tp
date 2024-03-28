package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

/**
 * Adds a schedule to the address book.
 */
public class AddSchedCommand extends Command {

    public static final String COMMAND_WORD = "addSched";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a schedule to person(s) in address book. "
            + "Parameters: "
            + "INDEX(S) (must be positive integer) "
            + PREFIX_SCHEDULE + " SCHEDULE "
            + PREFIX_START + " START DATETIME (yyyy-MM-dd HH:mm)"
            + PREFIX_END + " END DATETIME (yyyy-MM-dd HH:mm)"
            + "Example: " + COMMAND_WORD + " "
            + "1, 2"
            + PREFIX_SCHEDULE + " CS2103 weekly meeting "
            + PREFIX_START + " 2024-02-24 15:00"
            + PREFIX_END + " 2024-02-24 17:00";

    public static final String MESSAGE_SUCCESS = "New schedule added: %1$s";

    private final ArrayList<Index> targetIndexes;

    private final Schedule schedule;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddSchedCommand(ArrayList<Index> targetIndexes, Schedule schedule) {
        requireNonNull(schedule);
        this.targetIndexes = targetIndexes;
        this.schedule = schedule;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        ArrayList<Person> participants = new ArrayList<Person>();
        for (Index index : targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            participants.add(lastShownList.get(index.getZeroBased()));
        }


        schedule.addParticipants(participants);
        model.addSchedule(schedule, participants);

        return new CommandResult(generateSuccessMessage());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSchedCommand)) {
            return false;
        }

        AddSchedCommand asc = (AddSchedCommand) other;
        return targetIndexes.equals(asc.targetIndexes)
                && schedule.equals(asc.schedule);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person indexes", targetIndexes)
                .add("Schedule", schedule)
                .toString();
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage() {
        return String.format(MESSAGE_SUCCESS, schedule);
    }
}

