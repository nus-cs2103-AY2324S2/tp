package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Unassigns all tasks assigned to an existing person in the address book.
 */
public class ClearTaskCommand extends Command {

    public static final String COMMAND_WORD = "cleartask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns all tasks assigned to the person identified "
            + "by the index number used in the last person listing.\n"
            + "Parameters: PERSON_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to edit the remark
     */
    public ClearTaskCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, index.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearTaskCommand)) {
            return false;
        }

        ClearTaskCommand e = (ClearTaskCommand) other;
        return index.equals(e.index);
    }
}
