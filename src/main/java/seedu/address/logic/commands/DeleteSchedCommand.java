package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

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
public class DeleteSchedCommand extends Command {

    public static final String COMMAND_WORD = "deleteSched";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a schedule in address book. "
            + "Parameters: "
            + "TASK INDEX(S) (must be positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + "1";

    public static final String MESSAGE_SUCCESS = "The schedule deleted: %1$s";

    private final Index targetIndex;


    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public DeleteSchedCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> scheduleList = model.getFilteredScheduleList();

        Schedule scheduleToDelete = scheduleList.get(targetIndex.getZeroBased());
        model.deleteSchedule(scheduleToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(scheduleToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteSchedCommand)) {
            return false;
        }

        DeleteSchedCommand dsc = (DeleteSchedCommand) other;
        return targetIndex.equals(dsc.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person indexes", targetIndex)
                .toString();
    }
}


