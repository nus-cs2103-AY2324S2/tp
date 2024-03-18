package seedu.findvisor.logic.commands;

import static seedu.findvisor.commons.util.CollectionUtil.requireAllNonNull;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.commands.exceptions.CommandException;
import seedu.findvisor.model.Model;

/**
 * Edits a remark to an existing person in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a remark of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Plans to own a property by the age of 30.";

    public static final String MESSAGE_WORK_IN_PROGRESS = "Command is still a work in progress.";

    private final Index index;
    private final String remark;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark new remark of the person
     */
    public RemarkCommand(Index index, String remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_WORK_IN_PROGRESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand otherRemarkCommand = (RemarkCommand) other;
        return index.equals(otherRemarkCommand.index) && remark.equals(otherRemarkCommand.remark);
    }
}
