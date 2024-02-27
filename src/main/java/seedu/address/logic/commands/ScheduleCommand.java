package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Schedules a meeting with a contact.
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
     * Creates an AddCommand to add the specified {@code Person}
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

        assert !personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson);

        if (!DateTimeUtil.isAfterCurrentDateTime(meeting.start)) {
            throw new CommandException(MESSAGE_CANNOT_SCHEDULE_MEETING_IN_THE_PAST);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SCHEDULE_SUCCESS,
                editedPerson.getName(),
                DateTimeUtil.dateTimeToString(meeting.start),
                DateTimeUtil.dateTimeToString(meeting.end)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, Meeting meeting) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, Optional.of(meeting));
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
