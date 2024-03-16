package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Policy;

/**
 * Changes the policy of an existing person in the address book.
 */
public class PolicyCommand extends Command {

    public static final String COMMAND_WORD = "policy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the policy of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing policy will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_POLICY + "[POLICY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POLICY + "Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Policy: %2$s";

    private final Index index;
    private final Policy policy;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param policy of the person to be updated to
     */
    public PolicyCommand(Index index, Policy policy) {
        requireAllNonNull(index, policy);

        this.index = index;
        this.policy = policy;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), policy));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PolicyCommand)) {
            return false;
        }

        // state check
        PolicyCommand e = (PolicyCommand) other;
        return index.equals(e.index)
                && policy.equals(e.policy);
    }
}