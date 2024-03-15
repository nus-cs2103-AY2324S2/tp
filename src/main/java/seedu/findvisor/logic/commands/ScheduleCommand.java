package seedu.findvisor.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.findvisor.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.commons.util.DateTimeUtil;
import seedu.findvisor.commons.util.ToStringBuilder;
import seedu.findvisor.logic.Messages;
import seedu.findvisor.logic.commands.exceptions.CommandException;
import seedu.findvisor.model.Model;
import seedu.findvisor.model.person.Address;
import seedu.findvisor.model.person.Email;
import seedu.findvisor.model.person.Meeting;
import seedu.findvisor.model.person.Name;
import seedu.findvisor.model.person.Person;
import seedu.findvisor.model.person.Phone;
import seedu.findvisor.model.tag.Tag;

/**
 * Schedules a meeting with a person.
 */
public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules a meeting with the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_START_DATETIME + "START DATETIME "
            + PREFIX_END_DATETIME + "END DATETIME "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_START_DATETIME + "22-02-2024T14:00 "
            + PREFIX_END_DATETIME + "22-02-2024T15:00 ";

    public static final String MESSAGE_SCHEDULE_SUCCESS = "Scheduled meeting with %1$s from %2$s to %3$s";
    public static final String MESSAGE_CANNOT_SCHEDULE_MULTIPLE_MEETINGS = "Cannot schedule more than 1"
            + " meeting with a contact!";
    public static final String MESSAGE_CANNOT_SCHEDULE_MEETING_IN_THE_PAST = "Cannot schedule meeting that"
            + " starts before the current time!";

    private final Index targetIndex;
    private final Meeting meeting;

    /**
     * Creates an ScheduleCommand to schedule a meeting with the specified {@code Person}
     */
    public ScheduleCommand(Index targetIndex, Meeting meeting) {
        requireNonNull(meeting);
        this.targetIndex = targetIndex;
        this.meeting = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        if (personToEdit.getMeeting().isPresent()) {
            throw new CommandException(MESSAGE_CANNOT_SCHEDULE_MULTIPLE_MEETINGS);
        }
        Person editedPerson = createEditedPerson(personToEdit, meeting);

        if (!DateTimeUtil.isAfterCurrentDateTime(meeting.start)) {
            throw new CommandException(MESSAGE_CANNOT_SCHEDULE_MEETING_IN_THE_PAST);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SCHEDULE_SUCCESS,
                editedPerson.getName(),
                meeting.getStartString(),
                meeting.getEndString()));
    }

    /**
     * Creates and returns a {@code Person} with the same details of {@code personToEdit}
     * with the meeting scheduled.
     */
    private static Person createEditedPerson(Person personToEdit, Meeting meeting) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();

        return new Person(name, phone, email, address, tags, Optional.of(meeting));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        ScheduleCommand otherScheduleCommand = (ScheduleCommand) other;
        return targetIndex.equals(otherScheduleCommand.targetIndex)
                && meeting.equals(otherScheduleCommand.meeting);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toSchedule", targetIndex)
                .add("meeting", meeting)
                .toString();
    }
}
