package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Unarchives a person identified using it's displayed index from the address book.
 */
public class UnarchiveCommand extends Command {

    public static final String COMMAND_WORD = "unarchive";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_UNARCHIVE_PERSON_SUCCESS = "%1$s has been unarchived. Type “list” to "
            + "view all your currently active contacts.";
    public static final String MESSAGE_NOT_VIEWING_ARCHIVED = "This command can only be used while "
            + "viewing the archived list.";

    private final Index targetIndex;

    public UnarchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isViewingArchivedList()) {
            throw new CommandException(MESSAGE_NOT_VIEWING_ARCHIVED);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnarchive = lastShownList.get(targetIndex.getZeroBased());
        model.unarchivePerson(personToUnarchive);
        return new CommandResult(String.format(MESSAGE_UNARCHIVE_PERSON_SUCCESS,
                Messages.format(personToUnarchive)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnarchiveCommand)) {
            return false;
        }

        UnarchiveCommand otherUnarchiveCommand = (UnarchiveCommand) other;
        return targetIndex.equals(otherUnarchiveCommand.targetIndex);
    }
}
