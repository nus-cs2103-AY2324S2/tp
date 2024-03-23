package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NOTES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * Schedules a meeting with an existing person in the address book.
 */
public class ScheduleMeetingCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules a meeting with the person identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING_DATE + "[DATE] "
            + PREFIX_MEETING_TIME + "[TIME] "
            + PREFIX_MEETING_DURATION + "[DURATION in minutes] "
            + PREFIX_MEETING_AGENDA + "[AGENDA] "
            + PREFIX_MEETING_NOTES + "[NOTES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING_DATE + "2023-03-20 "
            + PREFIX_MEETING_TIME + "14:00 "
            + PREFIX_MEETING_DURATION + "60 "
            + PREFIX_MEETING_AGENDA + "Discuss new policy "
            + PREFIX_MEETING_NOTES + "Bring all necessary documents";

    public static final String MESSAGE_SUCCESS = "Meeting scheduled with Person: %1$s";

    private final Index index;
    private final Meeting meeting;

    /**
     * @param index   of the person in the filtered person list to schedule the meeting with
     * @param meeting details of the meeting to be scheduled
     */
    public ScheduleMeetingCommand(Index index, Meeting meeting) {
        requireAllNonNull(index, meeting);

        this.index = index;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMeet = lastShownList.get(index.getZeroBased());

        // Check for global meeting overlap
        if (model.hasMeetingOverlap(meeting)) {
            throw new CommandException("Meeting overlaps with existing meetings.");
        }

        try {
            personToMeet.addMeeting(meeting);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToMeet));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ScheduleMeetingCommand)) {
            return false;
        }
        //to dist

        ScheduleMeetingCommand e = (ScheduleMeetingCommand) other;
        return index.equals(e.index)
                && meeting.equals(e.meeting);
    }
}

