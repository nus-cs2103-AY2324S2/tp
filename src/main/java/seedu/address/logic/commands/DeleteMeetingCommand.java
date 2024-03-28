package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Deletes a meeting of a particular person using it's displayed index from the
 * address book.
 */
public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "deleteMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting of a particular client identified by the "
            + "index number used in the displayed client list.\n"
            + "Parameters: CLIENT_INDEX (must be a positive integer) MEETING_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Meeting %1$s deleted successfully ";

    private final Index clientIndex;
    private final Index meetingIndex;

    /**
     * Creates a DeleteMeetingCommand that would be responsible
     * for deleting a meeting in address book.
     */
    public DeleteMeetingCommand(Index clientIndex, Index meetingIndex) {
        this.clientIndex = clientIndex;
        this.meetingIndex = meetingIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetClient = lastShownList.get(clientIndex.getZeroBased());
        List<Meeting> lastShownMeetings = targetClient.getMeetings();

        if (meetingIndex.getZeroBased() >= lastShownMeetings.size()) {
            throw new CommandException(String.format(
                    Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX, meetingIndex.getOneBased()));
        }

        model.deleteSpecificMeetingForClient(clientIndex, meetingIndex);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, meetingIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMeetingCommand)) {
            return false;
        }

        DeleteMeetingCommand otherDeleteCommand = (DeleteMeetingCommand) other;
        return clientIndex.equals(otherDeleteCommand.clientIndex)
                && meetingIndex.equals(otherDeleteCommand.meetingIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clientIndex", clientIndex)
                .add("meetingIndex", meetingIndex)
                .toString();
    }
}
