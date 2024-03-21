package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.schedule.Schedule;

/**
 * Adds a schedule to the address book.
 */
public class DeleteSchedCommand extends Command {

    public static final String COMMAND_WORD = "deleteSched";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a schedule in address book. "
            + "Parameters: "
            + "TASK INDEX(S) (must be positive integer) "
            + PREFIX_SCHEDULE + "TO DELETE PERSON "
            + "Example: " + COMMAND_WORD + " " + "1"
            + PREFIX_SCHEDULE + " 1, 2";

    public static final String MESSAGE_SUCCESS = "The schedule deleted: %1$s";

    private final Index targetIndex;

    private final ArrayList<Index> deletedPersonIndexArrayList;


    /**
     * Creates an DeleteCommand to add the specified {@code Person}
     */
    public DeleteSchedCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.deletedPersonIndexArrayList = new ArrayList<Index>();
    }

    public DeleteSchedCommand(Index targetIndex, ArrayList<Index> deletedPersonIndexArrayList) {
        this.targetIndex = targetIndex;
        this.deletedPersonIndexArrayList = deletedPersonIndexArrayList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> scheduleList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= scheduleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
        }
        Schedule scheduleToDelete = scheduleList.get(targetIndex.getZeroBased());

        ArrayList<Person> allParticipants = scheduleToDelete.getPersonList();
        UniquePersonList toDeleteParticipants = new UniquePersonList();
        for (Index index : deletedPersonIndexArrayList) {
            if (index.getZeroBased() >= allParticipants.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            toDeleteParticipants.add(allParticipants.get(index.getZeroBased()));
        }
        if (deletedPersonIndexArrayList.isEmpty()) {
            deleteSchedForAll(model, scheduleToDelete);
            scheduleToDelete.setPersonList(new ArrayList<Person>());
        } else {
            deleteSchedForSpecificPersons(model, scheduleToDelete, toDeleteParticipants);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(scheduleToDelete)));
    }

    private void deleteSchedForAll(Model model, Schedule scheduleToDelete) {
        model.deleteSchedule(scheduleToDelete, scheduleToDelete.getPersonList());
    }

    private void deleteSchedForSpecificPersons(Model model, Schedule scheduleToDelete,
                                               UniquePersonList toDeleteParticipants) {
        Schedule scheduleToAdd = scheduleToDelete;
        model.deleteSchedule(scheduleToDelete, scheduleToDelete.getPersonList());
        ArrayList<Person> remainParticipants = new ArrayList<Person>();
        for (Person p: scheduleToDelete.getPersonList()) {
            if (!toDeleteParticipants.contains(p)) {
                p.deleteSchedule(scheduleToDelete);
                remainParticipants.add(p);
            }
        }
        scheduleToAdd.setPersonList(remainParticipants);
        if (!remainParticipants.isEmpty()) {
            model.addSchedule(scheduleToAdd, remainParticipants);
        }
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
                .add("Schedule index", targetIndex)
                .toString();
    }
}


