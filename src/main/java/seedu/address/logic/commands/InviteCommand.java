package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Invites a person to an event using their displayed index from the person list.
 */
public class InviteCommand extends Command {

    public static final String COMMAND_WORD = "inv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invites the person identified by the index number used in the displayed person list to "
            + "the selected event.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_INVITE_PERSON_SUCCESS = "Invited Person: %1$s to the selected event";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the selected event";

    private final Index targetIndex;

    /**
     * Constructor of InviteCommand
     *
     * @param targetIndex Index of person user wants to invite to selected event.
     */
    public InviteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isAnEventSelected()) {
            throw new CommandException(Messages.MESSAGE_SELECT_EVENT);
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToInvite = lastShownList.get(targetIndex.getZeroBased());
        if (model.isPersonInSelectedEvent(personToInvite)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPersonToSelectedEvent(personToInvite);
        return new CommandResult(String.format(MESSAGE_INVITE_PERSON_SUCCESS, personToInvite.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InviteCommand)) {
            return false;
        }

        InviteCommand otherInviteCommand = (InviteCommand) other;
        return targetIndex.equals(otherInviteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
