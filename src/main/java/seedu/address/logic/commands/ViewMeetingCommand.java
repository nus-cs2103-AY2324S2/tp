package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ViewMeetingCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "You are now viewing Meeting with index: ";
    private final Index clientIndex;
    private final Index meetingIndex;

    public ViewMeetingCommand(Index clientIndex, Index meetingIndex) {
        this.clientIndex = clientIndex;
        this.meetingIndex = meetingIndex;
    }

    /**
     * Executes the view command and updates the current view to show the details of the selected client
     * and their associated meetings.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the command result of the execution, indicating success.
     * @throws CommandException if the provided index is invalid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (this.clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person selectedClient = lastShownList.get(this.clientIndex.getZeroBased());
        ArrayList<Meeting> meetings = selectedClient.getMeetings();

        if (this.meetingIndex.getZeroBased() >= meetings.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting selectedMeeting = meetings.get(this.meetingIndex.getZeroBased());

        model.updateFilteredPersonList(c -> c.equals(selectedClient));
        model.updateFilteredMeetingList(m -> m.equals(selectedMeeting));

        return new CommandResult(MESSAGE_SUCCESS + this.clientIndex.getOneBased());
    }

    /**
     * Compares this ViewMeetingCommand with another object to check for equality.
     *
     * @param other the other object to compare with.
     * @return true if both commands are equal, otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewMeetingCommand)) {
            return false;
        }

        ViewMeetingCommand otherViewMeetingCommand = (ViewMeetingCommand) other;
        return clientIndex.equals(otherViewMeetingCommand.clientIndex);
    }
}
