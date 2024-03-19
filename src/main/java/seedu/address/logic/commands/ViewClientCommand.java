package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.MeetingBelongingToClientPredicate;
import seedu.address.model.person.Person;

/**
 * Represents a command to view a client in the address book.
 * This command selects a client based on the specified index
 * and updates the view to show the client's details and his/her associated meetings.
 */
public class ViewClientCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "You are now viewing Client with index: ";
    private final Index clientIndex;


    public ViewClientCommand(Index clientIndex) {
        this.clientIndex = clientIndex;
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

        model.updateFilteredPersonList(c -> c.equals(selectedClient));
        model.updateFilteredMeetingList(new MeetingBelongingToClientPredicate(selectedClient));


        return new CommandResult(MESSAGE_SUCCESS + this.clientIndex.getOneBased());
    }

    /**
     * Compares this ViewClientCommand with another object to check for equality.
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
        if (!(other instanceof ViewClientCommand)) {
            return false;
        }

        ViewClientCommand otherViewClientCommand = (ViewClientCommand) other;
        return clientIndex.equals(otherViewClientCommand.clientIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", clientIndex)
                .toString();
    }
}
