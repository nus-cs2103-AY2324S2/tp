package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditMeetingCommand extends Command {

    public static final String COMMAND_WORD = "editMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLIENT_INDEX + "CLIENT INDEX "
            + PREFIX_MEETING_INDEX + "MEETING INDEX "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATETIME + "DATETIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT_INDEX + "1 "
            + PREFIX_MEETING_INDEX + "2 "
            + PREFIX_NAME + "starbucks meeting "
            + PREFIX_DATETIME + "01-01-2024 12:00 ";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MEETING_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";

    private final Index meetingIndex;
    private final Index clientIndex;

    private final String description;

    private final LocalDateTime dateTime;

    /**
     * @param clientIndex           of the person in the filtered person list to edit
     * @param meetingIndex          of the meeting in the filtered meeting list to edit
     * @param description           details to edit the meeting with
     * @param dateTime              dateTime of meeting
     */
    public EditMeetingCommand(Index clientIndex, Index meetingIndex, String description, LocalDateTime dateTime) {
        requireNonNull(meetingIndex);
        requireNonNull(description);
        requireNonNull(clientIndex);
        requireNonNull(dateTime);

        this.meetingIndex = meetingIndex;
        this.clientIndex = clientIndex;
        this.description = description;
        this.dateTime = dateTime;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> clientList = model.getFilteredPersonList();

        if (clientIndex.getZeroBased() >= clientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person selectedClient = clientList.get(clientIndex.getZeroBased());
        List<Meeting> clientMeetingList = selectedClient.getMeetings();

        if (meetingIndex.getZeroBased() >= clientMeetingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = clientList.get(clientIndex.getZeroBased())
                .getMeetings().get(meetingIndex.getZeroBased());
        Meeting editedMeeting = new Meeting(description, dateTime, selectedClient);

        if (meetingToEdit.isSameMeeting(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        if (model.hasMeeting(editedMeeting) && selectedClient.hasExistingMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(clientIndex, meetingIndex);
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(editedMeeting.getDateTime(),
                editedMeeting.getDescription(), clientIndex);
        deleteMeetingCommand.execute(model);
        addMeetingCommand.execute(model);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, Messages.formatMeeting(editedMeeting)));
    }

    public Index getMeetingIndex() {
        return meetingIndex;
    }

    public Index getClientIndex() {
        return clientIndex;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMeetingCommand)) {
            return false;
        }

        EditMeetingCommand otherEditMeeting = (EditMeetingCommand) other;
        return meetingIndex.equals(otherEditMeeting.meetingIndex)
                && clientIndex.equals(otherEditMeeting.clientIndex)
                && description.equals(otherEditMeeting.description)
                && dateTime.equals(otherEditMeeting.dateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clientIndex", clientIndex)
                .add("meetingIndex", meetingIndex)
                .add("description", description)
                .add("dateTime", dateTime)
                .toString();
    }
}

